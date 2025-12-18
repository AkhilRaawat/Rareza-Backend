package com.rareza.order;

import com.rareza.common.enums.OrderStatus;
import com.rareza.common.enums.PaymentStatus;
import com.rareza.design.Design;
import com.rareza.design.DesignRepository;
import com.rareza.infrastructure.storage.UploadedImage;
import com.rareza.infrastructure.storage.UploadedImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DesignRepository designRepository;
    private final UploadedImageRepository uploadedImageRepository;

    public OrderService(OrderRepository orderRepository, 
                       DesignRepository designRepository,
                       UploadedImageRepository uploadedImageRepository) {
        this.orderRepository = orderRepository;
        this.designRepository = designRepository;
        this.uploadedImageRepository = uploadedImageRepository;
    }

    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        validateCreateOrderRequest(request);
        
        Order order = new Order();
        order.setSize(request.size());
        order.setDesignType(request.designType());
        order.setStatus(OrderStatus.CREATED);
        order.setPrice(request.price());
        order.setPaymentStatus(PaymentStatus.PENDING);
        
        // Set customer information
        order.setCustomerName(request.customerName());
        order.setCustomerEmail(request.customerEmail());
        order.setCustomerPhone(request.customerPhone());
        
        // Set delivery address
        order.setDeliveryAddressLine1(request.deliveryAddressLine1());
        order.setDeliveryAddressLine2(request.deliveryAddressLine2());
        order.setCity(request.city());
        order.setState(request.state());
        order.setPostalCode(request.postalCode());
        order.setCountry(request.country());
        
        // Set design source - exactly one must be provided
        setDesignSource(order, request);
        
        return orderRepository.save(order);
    }

    private void validateCreateOrderRequest(CreateOrderRequest request) {
        if (request == null) {
            throw new OrderCreationException("Order request cannot be null");
        }
        
        if (request.price() == null || request.price().compareTo(BigDecimal.ZERO) <= 0) {
            throw new OrderCreationException("Price must be greater than zero");
        }
        
        // Validate that exactly one design source is provided
        boolean hasDesignId = request.designId() != null;
        boolean hasUploadedImageId = request.uploadedImageId() != null;
        
        if (!hasDesignId && !hasUploadedImageId) {
            throw new OrderCreationException("Either design ID or uploaded image ID must be provided");
        }
        
        if (hasDesignId && hasUploadedImageId) {
            throw new OrderCreationException("Cannot provide both design ID and uploaded image ID");
        }
    }

    private void setDesignSource(Order order, CreateOrderRequest request) {
        if (request.designId() != null) {
            Design design = findActiveDesign(request.designId());
            order.setDesign(design);
        } else if (request.uploadedImageId() != null) {
            UploadedImage uploadedImage = findActiveUploadedImage(request.uploadedImageId());
            order.setUploadedImage(uploadedImage);
        }
    }

    private Design findActiveDesign(UUID designId) {
        return designRepository.findByIdAndIsActiveTrue(designId)
            .orElseThrow(() -> new OrderCreationException(
                "Active design not found with ID: " + designId));
    }

    private UploadedImage findActiveUploadedImage(UUID uploadedImageId) {
        return uploadedImageRepository.findByIdAndActiveTrue(uploadedImageId)
            .orElseThrow(() -> new OrderCreationException(
                "Active uploaded image not found with ID: " + uploadedImageId));
    }
}

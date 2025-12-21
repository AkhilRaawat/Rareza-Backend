package com.rareza.admin;

import com.rareza.admin.dto.AdminOrderActionResult;
import com.rareza.admin.dto.AdminOrderDetailResponse;
import com.rareza.admin.dto.AdminOrderSummary;
import com.rareza.common.enums.OrderStatus;
import com.rareza.order.Order;
import com.rareza.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    
    private final OrderRepository orderRepository;
    
    public AdminOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @Override
    @Transactional
    public AdminOrderActionResult approveOrder(UUID orderId) {
        Order order = findOrder(orderId);
        validateOrderCanBeProcessed(order);
        
        order.setStatus(OrderStatus.ADMIN_APPROVED);
        order.setRejectionReason(null);
        orderRepository.save(order);
        
        return new AdminOrderActionResult(
            order.getId(),
            order.getStatus(),
            null
        );
    }
    
    @Override
    @Transactional
    public AdminOrderActionResult rejectOrder(UUID orderId, String rejectionReason) {
        if (rejectionReason == null || rejectionReason.trim().isEmpty()) {
            throw new IllegalArgumentException("Rejection reason cannot be null or blank");
        }
        
        Order order = findOrder(orderId);
        validateOrderCanBeProcessed(order);
        
        order.setStatus(OrderStatus.ADMIN_REJECTED);
        order.setRejectionReason(rejectionReason);
        orderRepository.save(order);
        
        return new AdminOrderActionResult(
            order.getId(),
            order.getStatus(),
            order.getRejectionReason()
        );
    }
    
    private Order findOrder(UUID orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
    }
    
    private void validateOrderCanBeProcessed(Order order) {
        if (order.getStatus() != OrderStatus.CREATED) {
            throw new IllegalStateException("Order can only be approved or rejected when in CREATED status. Current status: " + order.getStatus());
        }
    }
    
    // Query operations (read-only, no @Transactional needed)
    @Override
    public List<AdminOrderSummary> getOrdersByStatus(OrderStatus status) {
        List<Order> orders = orderRepository.findByStatusOrderByCreatedAtAsc(status);
        return orders.stream()
                .map(this::mapToSummary)
                .toList();
    }

    @Override
    public List<AdminOrderSummary> getCreatedOrders() {
        return getOrdersByStatus(OrderStatus.CREATED);
    }

    @Override
    public AdminOrderDetailResponse getOrderDetail(UUID orderId) {
        Order order = findOrder(orderId);
        return mapToDetailResponse(order);
    }

    /**
     * Maps Order entity to AdminOrderSummary DTO.
     * Contains only essential fields for admin overview.
     *
     * @param order the order entity to map
     * @return mapped AdminOrderSummary DTO
     */
    private AdminOrderSummary mapToSummary(Order order) {
        return new AdminOrderSummary(
                order.getId(),
                order.getCustomerName(),
                order.getDesignType(),
                order.getSize(),
                order.getCreatedAt()
        );
    }

    /**
     * Maps Order entity to AdminOrderDetailResponse DTO.
     * Contains complete order information for admin detail view.
     *
     * @param order the order entity to map
     * @return mapped AdminOrderDetailResponse DTO
     */
    private AdminOrderDetailResponse mapToDetailResponse(Order order) {
        return new AdminOrderDetailResponse(
                order.getId(),
                order.getStatus(),
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getCustomerPhone(),
                order.getDeliveryAddressLine1(),
                order.getDeliveryAddressLine2(),
                order.getCity(),
                order.getState(),
                order.getCountry(),
                order.getPostalCode(),
                order.getSize(),
                order.getDesignType(),
                order.getDesign() != null ? order.getDesign().getId() : null,
                order.getUploadedImage() != null ? order.getUploadedImage().getId() : null,
                order.getRejectionReason(),
                order.getCreatedAt()
        );
    }
}

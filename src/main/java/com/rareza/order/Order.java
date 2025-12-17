package com.rareza.order;

import com.rareza.common.enums.DesignType;
import com.rareza.common.enums.OrderStatus;
import com.rareza.common.enums.TShirtSize;
import com.rareza.common.enums.PaymentStatus;
import com.rareza.design.Design;
import com.rareza.infrastructure.storage.UploadedImage;
import com.rareza.product.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;
import com.rareza.audit.AuditableEntity;

@Entity
@Table(name = "orders", indexes = {
    @Index(name = "idx_order_status", columnList = "status"),
    @Index(name = "idx_order_created_at", columnList = "created_at")
})
public class Order extends AuditableEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_id")
    private Design design;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_image_id")
    private UploadedImage uploadedImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TShirtSize size;

    @Enumerated(EnumType.STRING)
    @Column(name = "design_type", nullable = false)
    private DesignType designType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Lob
    @Column(name = "rejection_reason")
    private String rejectionReason;

    // Customer Information
    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_email", length = 150, nullable = false)
    private String customerEmail;

    @Column(name = "customer_phone", length = 20, nullable = false)
    private String customerPhone;

    // Delivery Address
    @Column(name = "delivery_address_line1", length = 255, nullable = false)
    private String deliveryAddressLine1;

    @Column(name = "delivery_address_line2", length = 255)
    private String deliveryAddressLine2;

    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "state", length = 100, nullable = false)
    private String state;

    @Column(name = "postal_code", length = 20, nullable = false)
    private String postalCode;

    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_order_id", length = 100)
    private String paymentOrderId;

    @Column(name = "payment_id", length = 100)
    private String paymentId;

    // Getters and Setters
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Design getDesign() { return design; }
    public void setDesign(Design design) { this.design = design; }

    public UploadedImage getUploadedImage() { return uploadedImage; }
    public void setUploadedImage(UploadedImage uploadedImage) { this.uploadedImage = uploadedImage; }

    public TShirtSize getSize() { return size; }
    public void setSize(TShirtSize size) { this.size = size; }

    public DesignType getDesignType() { return designType; }
    public void setDesignType(DesignType designType) { this.designType = designType; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getDeliveryAddressLine1() { return deliveryAddressLine1; }
    public void setDeliveryAddressLine1(String deliveryAddressLine1) { this.deliveryAddressLine1 = deliveryAddressLine1; }

    public String getDeliveryAddressLine2() { return deliveryAddressLine2; }
    public void setDeliveryAddressLine2(String deliveryAddressLine2) { this.deliveryAddressLine2 = deliveryAddressLine2; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getPaymentOrderId() { return paymentOrderId; }
    public void setPaymentOrderId(String paymentOrderId) { this.paymentOrderId = paymentOrderId; }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

}


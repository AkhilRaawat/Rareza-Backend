package com.rareza.admin.dto;

import com.rareza.common.enums.DesignType;
import com.rareza.common.enums.OrderStatus;
import com.rareza.common.enums.TShirtSize;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO for admin order detail information.
 * Contains complete order information for admin order detail view.
 */
public record AdminOrderDetailResponse(
    UUID orderId,
    OrderStatus status,
    String customerName,
    String customerEmail,
    String customerPhone,
    String deliveryAddressLine1,
    String deliveryAddressLine2,
    String city,
    String state,
    String country,
    String postalCode,
    TShirtSize size,
    DesignType designType,
    UUID designId,
    UUID uploadedImageId,
    String rejectionReason,
    Instant createdAt
) {
}

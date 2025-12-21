package com.rareza.admin.dto;

import com.rareza.common.enums.DesignType;
import com.rareza.common.enums.TShirtSize;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO for admin order summary information.
 * Contains only essential fields for admin order listing.
 */
public record AdminOrderSummary(
    UUID orderId,
    String customerName,
    DesignType designType,
    TShirtSize size,
    Instant createdAt
) {
}

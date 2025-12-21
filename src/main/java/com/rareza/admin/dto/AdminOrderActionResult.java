package com.rareza.admin.dto;

import com.rareza.common.enums.OrderStatus;

import java.util.UUID;

public record AdminOrderActionResult(
    UUID orderId,
    OrderStatus status,
    String rejectionReason
) {}

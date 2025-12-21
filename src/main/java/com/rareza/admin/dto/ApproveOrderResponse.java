package com.rareza.admin.dto;

import com.rareza.common.enums.OrderStatus;

import java.util.UUID;

public record ApproveOrderResponse(
        UUID orderId,
        OrderStatus status,
        String message
) {
}

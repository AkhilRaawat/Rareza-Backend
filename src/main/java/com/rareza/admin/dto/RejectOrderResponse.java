package com.rareza.admin.dto;

import com.rareza.common.enums.OrderStatus;
import java.util.UUID;

public record RejectOrderResponse(
        UUID orderId,
        OrderStatus status,
        String rejectionReason,
        String message
) {
}

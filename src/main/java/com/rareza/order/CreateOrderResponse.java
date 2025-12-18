package com.rareza.order;

import com.rareza.common.enums.OrderStatus;

import java.util.UUID;

public record CreateOrderResponse(
    UUID orderId,
    OrderStatus orderStatus
) {}

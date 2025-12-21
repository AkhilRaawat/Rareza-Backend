package com.rareza.admin;

import com.rareza.admin.dto.AdminOrderActionResult;
import com.rareza.admin.dto.AdminOrderDetailResponse;
import com.rareza.admin.dto.AdminOrderSummary;
import com.rareza.common.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface AdminOrderService {
    
    // Command operations (write)
    AdminOrderActionResult approveOrder(UUID orderId);
    
    AdminOrderActionResult rejectOrder(UUID orderId, String rejectionReason);
    
    // Query operations (read)
    List<AdminOrderSummary> getOrdersByStatus(OrderStatus status);
    
    List<AdminOrderSummary> getCreatedOrders();
    
    AdminOrderDetailResponse getOrderDetail(UUID orderId);
}

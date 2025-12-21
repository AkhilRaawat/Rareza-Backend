package com.rareza.admin;

import com.rareza.admin.dto.AdminOrderActionResult;
import com.rareza.admin.dto.AdminOrderDetailResponse;
import com.rareza.admin.dto.AdminOrderSummary;
import com.rareza.admin.dto.ApproveOrderResponse;
import com.rareza.admin.dto.RejectOrderRequest;
import com.rareza.admin.dto.RejectOrderResponse;
import com.rareza.common.enums.OrderStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    public AdminOrderController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    /**
     * Gets orders filtered by status, defaults to CREATED if status param is missing.
     *
     * @param status optional order status filter, defaults to CREATED
     * @return list of order summaries matching the criteria
     */
    @GetMapping
    public ResponseEntity<List<AdminOrderSummary>> getOrders(
            @RequestParam(value = "status", required = false) OrderStatus status) {
        
        // Default to CREATED status if not provided
        OrderStatus targetStatus = (status != null) ? status : OrderStatus.CREATED;
        
        List<AdminOrderSummary> orders = adminOrderService.getOrdersByStatus(targetStatus);
        
        return ResponseEntity.ok(orders);
    }

    /**
     * Gets detailed information for a specific order.
     *
     * @param orderId the ID of the order to retrieve
     * @return detailed order information
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<AdminOrderDetailResponse> getOrderDetail(@PathVariable UUID orderId) {
        AdminOrderDetailResponse orderDetail = adminOrderService.getOrderDetail(orderId);
        return ResponseEntity.ok(orderDetail);
    }

    @PostMapping("/{orderId}/approve")
    public ResponseEntity<ApproveOrderResponse> approveOrder(@PathVariable UUID orderId) {

        AdminOrderActionResult result = adminOrderService.approveOrder(orderId);

        return ResponseEntity.ok(
            new ApproveOrderResponse(
                result.orderId(),
                result.status(),
                "Order approved successfully"
            )
        );
    }

    @PostMapping("/{orderId}/reject")
    public ResponseEntity<RejectOrderResponse> rejectOrder(
            @PathVariable UUID orderId,
            @Valid @RequestBody RejectOrderRequest request) {

        AdminOrderActionResult result =
            adminOrderService.rejectOrder(orderId, request.rejectionReason());

        return ResponseEntity.ok(
            new RejectOrderResponse(
                result.orderId(),
                result.status(),
                result.rejectionReason(),
                "Order rejected successfully"
            )
        );
    }
}

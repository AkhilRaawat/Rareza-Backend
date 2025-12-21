package com.rareza.admin.dto;

import jakarta.validation.constraints.NotBlank;

public record RejectOrderRequest(
        @NotBlank(message = "Rejection reason must not be blank")
        String rejectionReason
) {
}

package com.rareza.order;

import com.rareza.common.enums.DesignType;
import com.rareza.common.enums.TShirtSize;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOrderRequest(
    UUID designId,
    UUID uploadedImageId,

    @NotNull TShirtSize size,
    @NotNull DesignType designType,

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    BigDecimal price,

    @NotBlank @Size(max = 100)
    String customerName,

    @NotBlank @Email @Size(max = 150)
    String customerEmail,

    @NotBlank @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$")
    String customerPhone,

    @NotBlank @Size(max = 255)
    String deliveryAddressLine1,

    @Size(max = 255)
    String deliveryAddressLine2,

    @NotBlank @Size(max = 100)
    String city,

    @NotBlank @Size(max = 100)
    String state,

    @NotBlank @Size(max = 20)
    String postalCode,

    @NotBlank @Size(max = 50)
    String country
) {}

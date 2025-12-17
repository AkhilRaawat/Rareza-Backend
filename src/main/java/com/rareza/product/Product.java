package com.rareza.product;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.rareza.audit.AuditableEntity;
import com.rareza.common.enums.ProductType;

@Entity
@Table(name = "products")
public class Product extends AuditableEntity {

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Lob
    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @Column(name = "base_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal basePrice;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ProductType getProductType() { return productType; }
    public void setProductType(ProductType productType) { this.productType = productType; }

    public BigDecimal getBasePrice() { return basePrice; }
    public void setBasePrice(BigDecimal basePrice) { this.basePrice = basePrice; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}

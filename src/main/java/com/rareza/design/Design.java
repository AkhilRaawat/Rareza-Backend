package com.rareza.design;

import com.rareza.audit.AuditableEntity;
import com.rareza.common.enums.DesignType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "designs")
public class Design extends AuditableEntity {

    @NotBlank(message = "Design name cannot be blank")
    @Size(max = 100, message = "Design name cannot exceed 100 characters")
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull(message = "Design type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DesignType designType;

    @Column(name = "preview_image_url", columnDefinition = "TEXT")
    private String previewImageUrl;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    // Getters and Setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public DesignType getDesignType() { return designType; }
    public void setDesignType(DesignType designType) { this.designType = designType; }

    public String getPreviewImageUrl() { return previewImageUrl; }
    public void setPreviewImageUrl(String previewImageUrl) { this.previewImageUrl = previewImageUrl; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}


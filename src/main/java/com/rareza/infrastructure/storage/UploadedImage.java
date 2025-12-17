package com.rareza.infrastructure.storage;

import com.rareza.audit.AuditableEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "uploaded_images")
public class UploadedImage extends AuditableEntity {

    @Column(name = "original_file_name", length = 255, nullable = false)
    private String originalFileName;

    @Column(name = "image_url", length = 1000, nullable = false)
    private String imageUrl; 
    // Must be CDN URL or signed URL (never raw S3 path)

    @Column(name = "file_size_bytes", nullable = false)
    private long fileSizeBytes;

    @Column(name = "mime_type", length = 50, nullable = false)
    private String mimeType;

    @Column(name = "is_temporary", nullable = false)
    private boolean temporary = true;
    // true → uploaded but not yet attached to approved order

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
    // false → soft-deleted or cleaned up

    /* ========= Getters ========= */

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getFileSizeBytes() {
        return fileSizeBytes;
    }

    public String getMimeType() {
        return mimeType;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public boolean isActive() {
        return active;
    }

    /* ========= Domain Methods (NO raw setters) ========= */

    public void markAsPermanent() {
        this.temporary = false;
    }

    public void deactivate() {
        this.active = false;
    }
}

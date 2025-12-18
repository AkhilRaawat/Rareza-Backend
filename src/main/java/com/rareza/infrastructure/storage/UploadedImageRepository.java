package com.rareza.infrastructure.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UploadedImageRepository extends JpaRepository<UploadedImage, UUID> {
    
    Optional<UploadedImage> findByIdAndActiveTrue(UUID id);
}

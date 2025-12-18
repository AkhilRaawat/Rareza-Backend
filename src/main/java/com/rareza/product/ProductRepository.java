package com.rareza.product;

import com.rareza.common.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    
    List<Product> findByIsActiveTrue();
    
    Optional<Product> findByNameAndIsActiveTrue(String name);
    
    List<Product> findByProductTypeAndIsActiveTrue(ProductType productType);
    
    Optional<Product> findByIdAndIsActiveTrue(UUID id);
    
    @Query("SELECT p FROM Product p WHERE p.isActive = true ORDER BY p.createdAt DESC")
    List<Product> findActiveProductsOrderByCreatedAtDesc();
    
    boolean existsByNameAndIsActiveTrue(String name);
}
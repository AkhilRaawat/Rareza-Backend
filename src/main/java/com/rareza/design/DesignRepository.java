package com.rareza.design;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DesignRepository extends JpaRepository<Design, UUID> {
    
    Optional<Design> findByIdAndIsActiveTrue(UUID id);
}

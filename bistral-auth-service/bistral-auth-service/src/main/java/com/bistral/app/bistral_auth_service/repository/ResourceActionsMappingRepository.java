package com.bistral.app.bistral_auth_service.repository;

import com.bistral.app.bistral_auth_service.entity.ResourceActionMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResourceActionsMappingRepository extends JpaRepository<ResourceActionMapping, UUID> {
    
    List<ResourceActionMapping> findAllWhereDeletedAtIsNullAndIsSystemSpecificIsFalse();
}

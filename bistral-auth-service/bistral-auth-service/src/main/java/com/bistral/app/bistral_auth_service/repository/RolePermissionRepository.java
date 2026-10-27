package com.bistral.app.bistral_auth_service.repository;

import com.bistral.app.bistral_auth_service.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, UUID> {

    @Modifying
    @Query("DELETE FROM RolePermission rp WHERE rp.role.userRoleId = :roleId")
    void deleteByRoleId(@Param("roleId") UUID roleId);
}


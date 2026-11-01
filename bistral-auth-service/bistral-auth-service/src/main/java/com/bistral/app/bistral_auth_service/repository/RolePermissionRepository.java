package com.bistral.app.bistral_auth_service.repository;

import com.bistral.app.bistral_auth_service.entity.TenantRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolePermissionRepository extends JpaRepository<TenantRolePermission, UUID> {

//    @Modifying
//    @Query("DELETE FROM TenantRolePermission rp WHERE rp.tenantRole.userRoleId = :roleId")
//    void deleteByRoleId(@Param("roleId") UUID roleId);
}


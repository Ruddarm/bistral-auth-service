package com.bistral.app.bistral_auth_service.repository;

import com.bistral.app.bistral_auth_service.entity.TenantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link TenantUser}.
 *
 * <p>
 * Provides CRUD operations for tenantUser data including support for
 * soft deletion (via deletedAt) and active status (isActive flag).
 * </p>
 *
 * <p>
 * Consumers should ensure filtering of inactive or soft-deleted users
 * where applicable at the service or query level.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<TenantUser, UUID> {

    /**
     * Find Active tenantUser by their  userName
     * <p>
     * <b>An Active tenantUser means</b>
     * <p>TenantUser with status active <code>true</code></p>
     * <p>Deleted At is null as active tenantUser can not be <code>null</code></p>
     * </p>
     *
     * @param userName represent username of tenantUser,
     * @return {@link  TenantUser}
     */
    Optional<TenantUser> findByUsernameAndIsActiveTrueAndDeletedAtIsNull(String userName);


    /**
     * Find Active tenantUser by their  userEmail
     * <p>
     * <b>An Active tenantUser means</b>
     * <p>TenantUser with status active <code>true</code></p>
     * <p>Deleted At is null as active tenantUser can not be <code>null</code></p>
     * </p>
     *
     * @param userName represent username of tenantUser,
     * @return {@link  TenantUser}
     */
    Optional<TenantUser> findByUserEmailAndIsActiveTrueAndDeletedAtIsNull(String userName);



}

package com.bistral.app.bistral_auth_service.repository;

import com.bistral.app.bistral_auth_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link Users}.
 *
 * <p>
 * Provides CRUD operations for user data including support for
 * soft deletion (via deletedAt) and active status (isActive flag).
 * </p>
 *
 * <p>
 * Consumers should ensure filtering of inactive or soft-deleted users
 * where applicable at the service or query level.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {

    /**
     * Find Active user by their  userName
     * <p>
     * <b>An Active user means</b>
     * <p>User with status active <code>true</code></p>
     * <p>Deleted At is null as active user can not be <code>null</code></p>
     * </p>
     *
     * @param userName represent username of user,
     * @return {@link  Users}
     */
    Optional<Users> findByUsernameAndIsActiveTrueAndDeletedAtIsNull(String userName);


    /**
     * Find Active user by their  userEmail
     * <p>
     * <b>An Active user means</b>
     * <p>User with status active <code>true</code></p>
     * <p>Deleted At is null as active user can not be <code>null</code></p>
     * </p>
     *
     * @param userName represent username of user,
     * @return {@link  Users}
     */
    Optional<Users> findByUserEmailAndIsActiveTrueAndDeletedAtIsNull(String userName);



}

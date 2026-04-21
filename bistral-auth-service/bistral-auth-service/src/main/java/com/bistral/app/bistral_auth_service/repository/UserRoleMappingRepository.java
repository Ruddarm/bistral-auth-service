package com.bistral.app.bistral_auth_service.repository;

import com.bistral.app.bistral_auth_service.entity.UserRoleMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMappingEntity, UUID> {

    @Query("""
                SELECT userRole
                FROM UserRoleMappingEntity userRole
                JOIN FETCH userRole.role
                WHERE userRole.user.userId = :userId
            """)
    List<UserRoleMappingEntity> findRolesOfUserByUserId(UUID userId);
}

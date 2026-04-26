package com.bistral.app.bistral_auth_service.repository;

import com.bistral.app.bistral_auth_service.entity.RoleEntity;
import com.bistral.app.bistral_auth_service.projection.RolePermissionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link RoleEntity}.
 * Provides CRUD operations as well as other custom data access method.
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {


    public Optional<RoleEntity> findByUserRoleIdAndBistroId(UUID roleId, UUID bistroId);


    @Query
            (value = """
                SELECT ur.user_role_id as roleId , ur.role_name as roleName ,\s
                              ur.bistro_id as bistroId  , rp.resource_id as resourceId , rp.action_id as actionId,
                              act.action_name as actionName,
                              re.resource_name as resourceName FROM public.user_roles ur left\s
                              join  role_permissions rp
                              on rp.user_role_id = ur.user_role_id
                              left join public.resources re
                              on rp.resource_id = re.resource_id
                              left join public.actions act on
                              rp.action_id = act.action_id
                              where ur.user_role_id = :roleId and
                              ur.bistro_id = :bistroId
            """, nativeQuery = true)
    public List<RolePermissionProjection> findRoleByIdAndBistroIdWithPermissions(UUID roleId, UUID bistroId);
}

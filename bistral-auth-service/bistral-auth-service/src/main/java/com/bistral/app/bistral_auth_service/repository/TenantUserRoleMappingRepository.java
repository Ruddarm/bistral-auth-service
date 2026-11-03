package com.bistral.app.bistral_auth_service.repository;

import com.bistral.app.bistral_auth_service.entity.TenantUserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TenantUserRoleMappingRepository extends JpaRepository<TenantUserRoleMapping, UUID> {

//    @Query("""
//                SELECT userRole
//                FROM UserRoleMapping userRole
//                JOIN FETCH userRole.tenantRole
//                WHERE userRole.tenantUser.userId = :userId
//            """)
//    List<TenantUserRoleMapping> findRolesOfUserByUserId(UUID userId);
//
//
//    /**
//     *d
//     * @param userId
//     * @param bistroId
//     * @param branchId
//     * @param roleId
//     * @return
//     */
//    @Query(value = """
//                SELECT r.resource_name || ':' || a.action_name AS permission
//                FROM user_role_mappings urm
//                JOIN user_roles ur
//                    ON ur.user_role_id = urm.role_id
//                JOIN role_permissions rp
//                    ON rp.user_role_id = ur.user_role_id
//                JOIN resources r
//                    ON r.resource_id = rp.resource_id
//                JOIN actions a
//                    ON a.action_id = rp.action_id
//                WHERE
//                    urm.user_id = :userId
//                    AND urm.bistro_id = :bistroId
//                    AND urm.branch_id = :branchId
//                    AND urm.role_id = :roleId
//            """, nativeQuery = true)
//    List<String> findPermissionByContext(UUID userId, UUID bistroId, UUID branchId, UUID roleId);
}

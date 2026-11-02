package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.annotation.HasPermission;
import com.bistral.app.bistral_auth_service.dtos.PageResponse;
import com.bistral.app.bistral_auth_service.dtos.RoleFilterRequest;
import com.bistral.app.bistral_auth_service.dtos.TenantRoleRequestDto;
import com.bistral.app.bistral_auth_service.dtos.TenantRoleResponseDto;
import com.bistral.app.bistral_auth_service.entity.TenantRole;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.projection.RolePermissionProjection;

import java.util.List;
import java.util.UUID;

/**
 * Provide interface for curd operation on {@link TenantRole}
 */
public interface TenantRoleCrudService {

    /**
     * Create {@link  TenantRole} for a given bistro id in {@link  TenantRoleRequestDto}
     * @param tenantRoleRequestDto represent high level abstraction of {@link  TenantRoleRequestDto}
     * @return {@link TenantRoleResponseDto} Only required field from {@link TenantRole}
     */
    @HasPermission("ROLE:CREATE")
    TenantRoleResponseDto createRole(TenantRoleRequestDto tenantRoleRequestDto) throws UserNotFoundException;

    /**
     * Get tenantRole by tenantRole id of bistro , but <code>tenantUser</code> have right  to access tenantRole.
     * @param roleId
     * @return
     */
    @HasPermission("ROLE:VIEW")
    TenantRole getRoleByIdAndBistroId(UUID roleId);

    /**
     * Get list of roles based on given filter criteria.
     * <p>
     * This request object contains optional filtering parameters such as:
     * <ul>
     *     <li>bistroId (mandatory) - to scope roles within a specific bistro</li>
     *     <li>roleName (optional) - to search roles by name (supports partial match)</li>
     *     <li>createdBy (optional) - filter roles created by a specific tenantUser</li>
     * </ul>
     *
     * If no optional filters are provided, all roles for the given bistro are returned.
     *
     * @param filterRequest encapsulates filtering criteria for fetching roles
     * @param page page number (optional, default = 0)
     * @param size number of records per page (optional, default = all)
     * @return paginated list of roles matching the filter criteria
     */
    @HasPermission("ROLE:VIEW")
    PageResponse<TenantRoleResponseDto> getListOfRoles(RoleFilterRequest filterRequest , Integer page , Integer size);


    /**
     *
     * @param roleId
     * @return
     */
    @HasPermission("ROLE:VIEW")
    List<RolePermissionProjection> getRoleByRoleIdBistroIdWithPermission(UUID roleId);
}

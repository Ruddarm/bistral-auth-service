package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.annotation.HasPermission;
import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.PageResponse;
import com.bistral.app.bistral_auth_service.dtos.RoleFilterRequest;
import com.bistral.app.bistral_auth_service.dtos.RoleRequestDto;
import com.bistral.app.bistral_auth_service.dtos.RoleResponseDto;
import com.bistral.app.bistral_auth_service.entity.RoleEntity;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.projection.RolePermissionProjection;
import org.hibernate.query.Page;

import java.util.List;
import java.util.UUID;

/**
 * Provide interface for curd operation on {@link com.bistral.app.bistral_auth_service.entity.RoleEntity}
 */
public interface RoleCrudService {

    /**
     * Create {@link  com.bistral.app.bistral_auth_service.entity.RoleEntity} for a given bistro id in {@link  RoleRequestDto}
     * @param roleRequestDto represent high level abstraction of {@link  RoleRequestDto}
     * @return {@link RoleResponseDto} Only required field from {@link com.bistral.app.bistral_auth_service.entity.RoleEntity}
     */
    @HasPermission("ROLE:CREATE")
    RoleResponseDto createRole(RoleRequestDto roleRequestDto) throws UserNotFoundException;

    /**
     * Get role by role id of bistro , but <code>user</code> have right  to access role.
     * @param roleId
     * @return
     */
    @HasPermission("ROLE:VIEW")
    RoleEntity getRoleByIdAndBistroId(UUID roleId);

    /**
     * Get list of roles based on given filter criteria.
     * <p>
     * This request object contains optional filtering parameters such as:
     * <ul>
     *     <li>bistroId (mandatory) - to scope roles within a specific bistro</li>
     *     <li>roleName (optional) - to search roles by name (supports partial match)</li>
     *     <li>createdBy (optional) - filter roles created by a specific user</li>
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
    PageResponse<RoleResponseDto> getListOfRoles(RoleFilterRequest filterRequest , Integer page , Integer size);


    /**
     *
     * @param roleId
     * @return
     */
    List<RolePermissionProjection> getRoleByRoleIdBistroIdWithPermission(UUID roleId);
}

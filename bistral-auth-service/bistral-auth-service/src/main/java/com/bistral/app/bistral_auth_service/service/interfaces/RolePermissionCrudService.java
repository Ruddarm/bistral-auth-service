package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.RolePermissionRequestDto;
import com.bistral.app.bistral_auth_service.dtos.RoleResponseDto;
import java.util.List;
import java.util.UUID;


/**
 *
 * Provide interface for assign permissions created by user
 * A permission is consist of role ,action and resource specify
 * what type of operation this role can perform in the given resource.
 *
 *
 */
public interface RolePermissionCrudService {

    /**
     *
     * @param roleId
     * @param rolePermissionRequests
     * @return
     */
    RoleResponseDto assignPermissionToRole(UUID roleId, List<RolePermissionRequestDto> rolePermissionRequests);
}

package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.annotation.HasPermission;
import com.bistral.app.bistral_auth_service.dtos.BistroContextDto;
import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingRequestDto;
import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingResponseDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 */
public interface RoleUserMappingCrudService {

    @HasPermission("ROLE:VIEW")
    Boolean assignRole(UserRoleMappingRequestDto roleMappingRequestDto);

//    @HasPermission("ROLE:VIEW")
    Map<UUID, BistroContextDto> getRolesOfUser(UUID userId);

    @HasPermission("ROLE:VIEW")
    List<String> getListOfPermissionForUser(UUID userId, UUID bistroId, UUID branchId, UUID roleId);
}

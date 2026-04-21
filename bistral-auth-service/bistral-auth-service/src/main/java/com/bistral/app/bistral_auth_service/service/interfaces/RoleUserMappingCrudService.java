package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingRequestDto;
import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingResponseDto;

import java.util.UUID;

/**
 *
 */
public interface RoleUserMappingCrudService {

     Boolean assignRole(UserRoleMappingRequestDto roleMappingRequestDto);

     UserRoleMappingResponseDto getRolesOfUser(UUID userId);
}

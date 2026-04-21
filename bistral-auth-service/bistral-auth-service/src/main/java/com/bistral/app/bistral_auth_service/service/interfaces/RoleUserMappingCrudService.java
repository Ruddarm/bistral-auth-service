package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingRequestDto;
import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingResponseDto;

/**
 *
 */
public interface RoleUserMappingCrudService {

     Boolean assignRole(UserRoleMappingRequestDto roleMappingRequestDto);

}

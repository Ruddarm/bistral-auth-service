package com.bistral.app.bistral_auth_service.dtos;

import javax.management.relation.Role;
import java.util.Map;
import java.util.UUID;

public class UserRoleMappingResponseDto {


    Map<UUID, RoleAssignmentDto> roleAssignmentDtoMap;
}

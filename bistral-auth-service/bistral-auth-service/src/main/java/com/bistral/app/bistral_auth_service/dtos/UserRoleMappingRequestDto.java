package com.bistral.app.bistral_auth_service.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserRoleMappingRequestDto {

    @NotBlank
    private UUID roleId;
    @NotBlank
    private UUID userId;
    @NotBlank
    private UUID bistroId;

    private List<RoleAssignmentDto> roleAssignmentDtoList;

}

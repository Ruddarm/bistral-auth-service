package com.bistral.app.bistral_auth_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserRoleMappingRequestDto {


    @NotNull
    private UUID userId;
    @NotNull
    private UUID bistroId;

    @NotNull
    @NotEmpty
    private List<RoleAssignmentDto> roleAssignmentDtoList;

}

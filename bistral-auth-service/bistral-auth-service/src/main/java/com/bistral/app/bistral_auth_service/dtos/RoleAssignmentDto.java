package com.bistral.app.bistral_auth_service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class RoleAssignmentDto {

    @NotNull
    private UUID roleId;
    @NotNull
    private UUID branchId;
}

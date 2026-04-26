package com.bistral.app.bistral_auth_service.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 *
 */
@Data
@Builder
public class RolePermissionRequestDto {
    @NotNull
    UUID resourceId;
    @NotNull
    UUID actionId;

    @NotNull
    Boolean isAllowed;
}

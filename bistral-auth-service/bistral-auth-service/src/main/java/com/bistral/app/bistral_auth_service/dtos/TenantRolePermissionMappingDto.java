package com.bistral.app.bistral_auth_service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Builder
@Data
public class TenantRolePermissionMappingDto {
    @NotNull
    private UUID tenantRoleId;
    
    @NotNull
    private UUID resourceActionMappingId;
    
    @NotNull
    @Builder.Default
    private boolean isAllowed = false;
}

package com.bistral.app.bistral_auth_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TenantRoleRequestDto {
    @NotNull
    @NotBlank
    private String roleName;
    
    @NotNull
    @NotBlank
    private UUID parentRoleId;
    
    @NotNull
    private List<TenantRolePermissionMappingDto> tenantRolePermissionMappings;

}

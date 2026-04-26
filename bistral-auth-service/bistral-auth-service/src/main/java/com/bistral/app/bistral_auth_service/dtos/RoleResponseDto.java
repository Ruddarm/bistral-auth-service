package com.bistral.app.bistral_auth_service.dtos;

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
public class RoleResponseDto {

    private UUID userRoleId;
    private String roleName;
    private List<RolePermissionResponseDto> rolePermissionResponses;
}

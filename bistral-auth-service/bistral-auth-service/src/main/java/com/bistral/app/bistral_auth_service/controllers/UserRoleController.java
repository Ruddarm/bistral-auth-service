package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import com.bistral.app.bistral_auth_service.dtos.TenantRoleRequestDto;
import com.bistral.app.bistral_auth_service.dtos.TenantRoleResponseDto;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.mapper.RoleMapper;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantRoleCrudService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class UserRoleController {

    private final TenantRoleCrudService tenantRoleCrudService;
    private final RoleMapper roleMapper;

    @PostMapping("")
    public ApiResponse<TenantRoleResponseDto> createRole(@Valid @RequestBody TenantRoleRequestDto tenantRoleRequestDto) throws UserNotFoundException {
        return ApiResponse.<TenantRoleResponseDto>builder()
                .isError(false)
                .message("TenantRole Created Successfully")
                .data(tenantRoleCrudService.createRole(tenantRoleRequestDto))
                .build();
    }

    @GetMapping("/{roleId}")
    public ApiResponse<TenantRoleResponseDto> getRoleById(@PathVariable UUID roleId) {
        return ApiResponse.<TenantRoleResponseDto>builder()
                .data(roleMapper
                        .toRoleResponseWithPermission(tenantRoleCrudService.getRoleByRoleIdBistroIdWithPermission(roleId)))
                .message("TenantRole found")
                .build();
    }

    @GetMapping("/bistro/{bistroId}")
    public ApiResponse<TenantRoleResponseDto> getRoleByBistroId() {
        return null;
    }


}

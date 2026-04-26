package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.annotation.HasPermission;
import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import com.bistral.app.bistral_auth_service.dtos.RoleRequestDto;
import com.bistral.app.bistral_auth_service.dtos.RoleResponseDto;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.mapper.RoleMapper;
import com.bistral.app.bistral_auth_service.service.interfaces.RoleCrudService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class UserRoleController {

    private final RoleCrudService roleCrudService;
    private final RoleMapper roleMapper;

    @PostMapping("")
    public ApiResponse<RoleResponseDto> createRole(@Valid @RequestBody RoleRequestDto roleRequestDto) throws UserNotFoundException {
        return ApiResponse.<RoleResponseDto>builder()
                .isError(false)
                .message("Role Created Successfully")
                .data(roleCrudService.createRole(roleRequestDto))
                .build();
    }

    @GetMapping("/{roleId}")
    public ApiResponse<RoleResponseDto> getRoleById(@PathVariable UUID roleId) {
        return ApiResponse.<RoleResponseDto>builder()
                .data(roleMapper
                        .toRoleResponseWithPermission(roleCrudService.getRoleByRoleIdBistroIdWithPermission(roleId)))
                .message("Role found")
                .build();
    }

    @GetMapping("/bistro/{bistroId}")
    public ApiResponse<RoleResponseDto> getRoleByBistroId() {
        return null;
    }


}

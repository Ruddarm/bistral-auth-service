package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import com.bistral.app.bistral_auth_service.dtos.RolePermissionRequestDto;
import com.bistral.app.bistral_auth_service.dtos.RoleResponseDto;
import com.bistral.app.bistral_auth_service.service.interfaces.RolePermissionCrudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles/{roleId}/permissions")
public class RolePermissionController {

    private final RolePermissionCrudService rolePermissionCrudService;


    @PostMapping()
    public ResponseEntity<ApiResponse<RoleResponseDto>> assignPermissionToRole(@PathVariable UUID roleId, @Valid @RequestBody List<RolePermissionRequestDto> rolePermissionRequestDtoList) {
        return ResponseEntity
                .ok(
                        ApiResponse.<RoleResponseDto>builder()
                                .message("Permission Assigned to Roles Success fully")
                                .data(rolePermissionCrudService
                                        .assignPermissionToRole(roleId,rolePermissionRequestDtoList))
                                .build()
                );
    }


}

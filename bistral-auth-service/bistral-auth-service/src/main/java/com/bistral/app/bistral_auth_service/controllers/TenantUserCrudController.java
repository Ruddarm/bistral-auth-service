package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import com.bistral.app.bistral_auth_service.dtos.TenantUserResponseDto;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.mapper.UserMapper;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantUserCrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * API Endpoint for tenantUser crud operations.
 *
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class TenantUserCrudController {

    private final TenantUserCrudService tenantUserCrudService;
    private final UserMapper userMapper;


    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<TenantUserResponseDto>> findUserById(@PathVariable UUID userId) throws UserNotFoundException {
        return ResponseEntity
                .ok(ApiResponse.<TenantUserResponseDto>
                                builder()
                        .data(
                                userMapper.toUserResponseDto(tenantUserCrudService.getTenantUserByUserId(userId))
                        )
                        .message("tenantUser fetch success fully")
                        .build());
    }


}

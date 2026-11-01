package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import com.bistral.app.bistral_auth_service.dtos.CreateUserDto;
import com.bistral.app.bistral_auth_service.dtos.UserResponseDto;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.mapper.UserMapper;
import com.bistral.app.bistral_auth_service.service.interfaces.UserCrudService;
import jakarta.validation.Valid;
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
public class UserCrudController {

    private final UserCrudService userCrudService;
    private final UserMapper userMapper;


    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDto>> findUserById(@PathVariable UUID userId) throws UserNotFoundException {
        return ResponseEntity
                .ok(ApiResponse.<UserResponseDto>
                                builder()
                        .data(
                                userMapper.toUserResponseDto(userCrudService.getUserById(userId))
                        )
                        .message("tenantUser fetch success fully")
                        .build());
    }


}

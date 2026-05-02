package com.bistral.app.bistral_auth_service.controllers;

import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import com.bistral.app.bistral_auth_service.dtos.BistroContextDto;
import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingRequestDto;
import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingResponseDto;
import com.bistral.app.bistral_auth_service.service.interfaces.RoleUserMappingCrudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users/role")
public class UserRoleMappingController {

    private final RoleUserMappingCrudService roleUserMappingCrudService;


    @PostMapping("/map")
    public ResponseEntity<ApiResponse<Boolean>> assignRole(
            @Valid @RequestBody UserRoleMappingRequestDto userRoleMappingRequestDto
    ) {
        return
                new ResponseEntity<ApiResponse<Boolean>>(
                        ApiResponse.<Boolean>builder()
                                .data(roleUserMappingCrudService.assignRole(userRoleMappingRequestDto))
                                .message("Role Assigned Success Fully")
                                .isError(false)
                                .build()
                        , HttpStatus.CREATED
                );
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<BistroContextDto>>> getRoleOfUsers() {
        return ResponseEntity.ok(
                ApiResponse.<List<BistroContextDto>>builder()
                        .message("role mapping found successfully")
                        .data(roleUserMappingCrudService.getRolesOfUser())
                        .build()
        );
    }

}

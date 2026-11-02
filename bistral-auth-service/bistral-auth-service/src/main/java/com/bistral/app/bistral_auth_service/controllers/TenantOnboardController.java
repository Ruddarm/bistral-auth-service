package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import com.bistral.app.bistral_auth_service.dtos.OnboardTenantUserDto;
import com.bistral.app.bistral_auth_service.dtos.TenantUserResponseDto;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantUserCrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/onboarding")
@AllArgsConstructor
public class TenantOnboardController {


    private TenantUserCrudService tenantUserCrudService;

    /**
     * Endpoint to create first user of a tenant to identify
     */
    @PostMapping("/user")
    public ResponseEntity<ApiResponse<TenantUserResponseDto>> onboardTenantUser(@RequestBody OnboardTenantUserDto onboardTenantUserDto) {
        return new ResponseEntity<ApiResponse<TenantUserResponseDto>>(
                ApiResponse.<TenantUserResponseDto>builder()
                        .data(tenantUserCrudService.onboardTenantFirstUser(onboardTenantUserDto))
                        .message("User Onboarded Successfully")
                        .isError(false)
                        .build(),
                HttpStatus.CREATED
        );
    }



    @PostMapping("/setup")
    public void setupTenantOnboard() {

    }

}

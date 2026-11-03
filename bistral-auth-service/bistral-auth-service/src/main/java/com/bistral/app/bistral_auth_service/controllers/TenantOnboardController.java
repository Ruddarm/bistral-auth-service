package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import com.bistral.app.bistral_auth_service.dtos.OnboardTenantUserDto;
import com.bistral.app.bistral_auth_service.dtos.TenantOnboardingSetUpDto;
import com.bistral.app.bistral_auth_service.dtos.TenantUserResponseDto;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantOnboardingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/onboarding")
@AllArgsConstructor
public class TenantOnboardController {


    private final TenantOnboardingService tenantOnboardingService;

    /**
     * Endpoint to create first user of a tenant to identify
     */
    @PostMapping("/user")
    public ResponseEntity<ApiResponse<TenantUserResponseDto>> onboardTenantUser(@RequestBody OnboardTenantUserDto onboardTenantUserDto) {
        return new ResponseEntity<ApiResponse<TenantUserResponseDto>>(
                ApiResponse.<TenantUserResponseDto>builder()
                        .data(tenantOnboardingService.onboardTenantUser(onboardTenantUserDto))
                        .message("User Onboarded Successfully")
                        .isError(false)
                        .build(),
                HttpStatus.CREATED
        );
    }


    @PostMapping("/setup")
    public ResponseEntity<ApiResponse<Void>> setupTenantOnboard(@RequestBody TenantOnboardingSetUpDto tenantOnboardingSetUpDto) throws UserNotFoundException {
        tenantOnboardingService.tenantOnBoardSetup(tenantOnboardingSetUpDto);
        return ResponseEntity.ok(
                ApiResponse.ok("Tenant Onboard Successfully")
        );

    }

}

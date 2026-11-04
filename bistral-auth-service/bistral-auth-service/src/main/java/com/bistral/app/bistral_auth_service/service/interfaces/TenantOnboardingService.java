package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.annotation.HasPermission;
import com.bistral.app.bistral_auth_service.dtos.OnboardTenantUserDto;
import com.bistral.app.bistral_auth_service.dtos.TenantOnboardingSetUpDto;
import com.bistral.app.bistral_auth_service.dtos.TenantUserResponseDto;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface TenantOnboardingService {


    @HasPermission("tenant:onboard")
    TenantUserResponseDto onboardTenantUser(OnboardTenantUserDto onboardTenantUserDto);

    void tenantOnBoardSetup(TenantOnboardingSetUpDto tenantOnboardingSetUpDto) throws UserNotFoundException;

}

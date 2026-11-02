package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.annotation.HasPermission;
import com.bistral.app.bistral_auth_service.dtos.OnboardTenantUserDto;
import com.bistral.app.bistral_auth_service.dtos.OnboardingSetUpDto;
import com.bistral.app.bistral_auth_service.dtos.TenantUserResponseDto;
import com.bistral.app.bistral_auth_service.entity.TenantRole;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantOnboardingService;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantUserCrudService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class TenantOnboardingServiceImpl implements TenantOnboardingService {


    private final TenantUserCrudService tenantUserCrudService;
    private final TenantRoleCrudServiceImpl tenantRoleCrudService;


    /**
     * Onboard first user of tenant
     * @param onboardTenantUserDto
     * @return
     */
    @HasPermission("tenant:onboard")
    public TenantUserResponseDto onboardTenantUser(OnboardTenantUserDto onboardTenantUserDto) {
        return tenantUserCrudService.onboardTenantFirstUser(onboardTenantUserDto);
    }


    @Override
    public void onBoardTenant(UUID tenantId) {

    }
}

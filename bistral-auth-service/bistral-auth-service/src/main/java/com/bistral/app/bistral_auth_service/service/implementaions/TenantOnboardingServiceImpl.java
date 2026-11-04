package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.annotation.HasPermission;
import com.bistral.app.bistral_auth_service.dtos.OnboardTenantUserDto;
import com.bistral.app.bistral_auth_service.dtos.TenantOnboardingSetUpDto;
import com.bistral.app.bistral_auth_service.dtos.TenantUserResponseDto;
import com.bistral.app.bistral_auth_service.entity.TenantRole;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantOnboardingService;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantUserCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TenantOnboardingServiceImpl implements TenantOnboardingService {


    private final TenantUserCrudService tenantUserCrudService;
    private final TenantRoleCrudServiceImpl tenantRoleCrudService;


    /**
     * Onboard first user of tenant
     *
     * @param onboardTenantUserDto
     * @return
     */
    @HasPermission("tenant:onboard")
    @Override
    public TenantUserResponseDto onboardTenantUser(OnboardTenantUserDto onboardTenantUserDto) {
        return tenantUserCrudService.onboardTenantFirstUser(onboardTenantUserDto);
    }


    /**
     *
     * @param tenantOnboardingSetUpDto
     * @throws UserNotFoundException
     */
    @Override
    public void tenantOnBoardSetup(TenantOnboardingSetUpDto tenantOnboardingSetUpDto) throws UserNotFoundException {

        TenantUser tenantUser = tenantUserCrudService.getTenantUserByUserId(
                tenantOnboardingSetUpDto.getUserId()
        );

        TenantRole rootRole = tenantRoleCrudService.createRootRole(
                tenantOnboardingSetUpDto.getTenantId(),
                tenantOnboardingSetUpDto.getBistroId(),
                tenantUser
        );


    }


}

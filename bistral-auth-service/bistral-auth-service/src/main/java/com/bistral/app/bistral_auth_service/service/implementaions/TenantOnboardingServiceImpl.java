package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.entity.TenantRole;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantOnboardingService;

import java.util.UUID;

public class TenantOnboardingServiceImpl implements TenantOnboardingService {
    
    
    @Override
    public void onBoardTenant(UUID tenantId) {
        //* fetch tenant details.
        //Create Root Role;
        
        TenantRole rootRole = new  TenantRole();
        rootRole.setTenantId(tenantId);
    }
}

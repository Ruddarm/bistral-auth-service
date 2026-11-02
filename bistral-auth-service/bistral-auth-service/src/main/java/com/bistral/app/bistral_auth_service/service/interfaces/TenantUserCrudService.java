package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.annotation.HasPermission;
import com.bistral.app.bistral_auth_service.dtos.CreateTenantUserDto;
import com.bistral.app.bistral_auth_service.dtos.OnboardTenantUserDto;
import com.bistral.app.bistral_auth_service.dtos.TenantUserResponseDto;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

/**
 * provide interface for crud service of global {@link  TenantUser}
 *
 */
public interface TenantUserCrudService {

    /**
     * Create tenantUser in system.
     * @param userSignUpDto required information for creation of tenantUser
     *
     * @return {@link  TenantUserResponseDto} represent abstraction of {@link TenantUser}
     */
    public TenantUserResponseDto createTenantUser(CreateTenantUserDto userSignUpDto);

    TenantUserResponseDto onboardTenantFirstUser(OnboardTenantUserDto onboardTenantUserDto);

    /**
     * Find and Return tenantUser of a given id
     * @param userId represent userid
     * @return {@link TenantUser} represent tenantUser belong to given userId.
     */
    public TenantUser getTenantUserByUserId(UUID userId) throws UserNotFoundException;




}

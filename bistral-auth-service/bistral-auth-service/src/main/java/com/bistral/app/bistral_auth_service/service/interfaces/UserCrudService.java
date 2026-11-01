package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.CreateUserDto;
import com.bistral.app.bistral_auth_service.dtos.UserResponseDto;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

/**
 * provide interface for crud service of global {@link  TenantUser}
 *
 */
public interface UserCrudService extends UserDetailsService {

    /**
     * Create tenantUser in system.
     * @param userSignUpDto required information for creation of tenantUser
     *
     * @return {@link  UserResponseDto} represent abstraction of {@link TenantUser}
     */
    public UserResponseDto createUser(CreateUserDto userSignUpDto);

    /**
     * Find and Return tenantUser of a given id
     * @param userId represent userid
     * @return {@link TenantUser} represent tenantUser belong to given userId.
     */
    public TenantUser getUserById(UUID userId) throws UserNotFoundException;




}

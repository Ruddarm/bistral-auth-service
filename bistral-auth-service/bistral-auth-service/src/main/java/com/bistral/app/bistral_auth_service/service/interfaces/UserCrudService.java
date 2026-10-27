package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.CreateUserDto;
import com.bistral.app.bistral_auth_service.dtos.UserResponseDto;
import com.bistral.app.bistral_auth_service.entity.Users;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

/**
 * provide interface for crud service of global {@link  Users}
 *
 */
public interface UserCrudService extends UserDetailsService {

    /**
     * Create user in system.
     * @param userSignUpDto required information for creation of user
     *
     * @return {@link  UserResponseDto} represent abstraction of {@link Users}
     */
    public UserResponseDto createUser(CreateUserDto userSignUpDto);

    /**
     * Find and Return user of a given id
     * @param userId represent userid
     * @return {@link Users} represent user belong to given userId.
     */
    public Users getUserById(UUID userId) throws UserNotFoundException;




}

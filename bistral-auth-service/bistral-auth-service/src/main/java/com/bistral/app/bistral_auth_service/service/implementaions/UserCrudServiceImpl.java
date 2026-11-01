package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.dtos.CreateUserDto;
import com.bistral.app.bistral_auth_service.dtos.UserResponseDto;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.enums.UserType;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.repository.UserRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.UserCrudService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of {@link  UserCrudService} to provide crud operation for {@link  TenantUser}
 *
 */
@AllArgsConstructor
@Service
public class UserCrudServiceImpl implements UserCrudService, UserDetailsService {

    private final UserRepository userCrudRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     *
     * @param userSignUpDto required information for creation of tenantUser
     * @return
     */
    @Override
    public UserResponseDto createUser(CreateUserDto userSignUpDto) {
        TenantUser tenantUser =
                TenantUser.builder()
                        .userEmail(userSignUpDto.getUserEmail())
                        .username(userSignUpDto.getUserName())
                        .build();
        tenantUser.setUserHashPasswd(
                bCryptPasswordEncoder.encode(userSignUpDto.getPassword())
        );
        //TODO As of new we creating system tenantUser by default
        tenantUser = userCrudRepository.save(tenantUser);
        return UserResponseDto.builder()
                .userEmail(tenantUser.getUserEmail())
                .username(tenantUser.getUsername())
                .userId(tenantUser.getUserId())
                .build();
    }

    @Override
    public TenantUser getUserById(UUID userId) throws UserNotFoundException {
        return userCrudRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId, "TenantUser not found with given Id " + userId));
    }


    /**
     * get tenantUser by username or userEmail based on given input
     *
     * @param username can be userName or userEmail
     * @return {@link  UserDetails} represent tenantUser in a system
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userCrudRepository
                .findByUsernameAndIsActiveTrueAndDeletedAtIsNull(username)
                .orElse(userCrudRepository.findByUserEmailAndIsActiveTrueAndDeletedAtIsNull(username)
                        .orElseThrow(() -> new UsernameNotFoundException("TenantUser " + username + "not found "))
                );

    }
}

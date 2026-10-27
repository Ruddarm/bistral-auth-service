package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.dtos.CreateUserDto;
import com.bistral.app.bistral_auth_service.dtos.UserResponseDto;
import com.bistral.app.bistral_auth_service.entity.Users;
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
 * Implementation of {@link  UserCrudService} to provide crud operation for {@link  Users}
 *
 */
@AllArgsConstructor
@Service
public class UserCrudServiceImpl implements UserCrudService, UserDetailsService {

    private final UserRepository userCrudRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     *
     * @param userSignUpDto required information for creation of user
     * @return
     */
    @Override
    public UserResponseDto createUser(CreateUserDto userSignUpDto) {
        Users users =
                Users.builder()
                        .userEmail(userSignUpDto.getUserEmail())
                        .username(userSignUpDto.getUserName())
                        .build();
        users.setUserHashPasswd(
                bCryptPasswordEncoder.encode(userSignUpDto.getPassword())
        );
        //TODO As of new we creating system user by default
        users.setUserTypeEnum(UserType.SYSTEM_USER);
        users = userCrudRepository.save(users);
        return UserResponseDto.builder()
                .userEmail(users.getUserEmail())
                .username(users.getUsername())
                .userId(users.getUserId())
                .userTypeEnum(users.getUserTypeEnum())
                .build();
    }

    @Override
    public Users getUserById(UUID userId) throws UserNotFoundException {
        return userCrudRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId, "User not found with given Id " + userId));
    }


    /**
     * get user by username or userEmail based on given input
     *
     * @param username can be userName or userEmail
     * @return {@link  UserDetails} represent user in a system
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userCrudRepository
                .findByUsernameAndIsActiveTrueAndDeletedAtIsNull(username)
                .orElse(userCrudRepository.findByUserEmailAndIsActiveTrueAndDeletedAtIsNull(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found "))
                );

    }
}

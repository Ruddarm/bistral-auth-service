package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.AuthResponse;
import com.bistral.app.bistral_auth_service.dtos.LoginContext;
import com.bistral.app.bistral_auth_service.dtos.UserLoginRequest;
import com.bistral.app.bistral_auth_service.entity.UserEntity;
import com.bistral.app.bistral_auth_service.service.interfaces.AuthService;
import com.bistral.app.bistral_auth_service.service.interfaces.JwtService;
import com.bistral.app.bistral_auth_service.service.interfaces.UserCrudService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Service implementation responsible for handling authentication workflows.
 *
 * Authenticates user credentials using Spring Security's AuthenticationManager
 * and generates JWT access and refresh tokens via JwtService.
 */
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRoleMappingCrudServiceImpl userRoleMappingCrudService;
    private final UserCrudService userCrudService;

    /**
     * Authenticates user credentials and returns JWT tokens.
     *
     * @param loginRequest contains username and password
     * @return AuthResponse containing access token, refresh token, and user details
     * @throws Exception if authentication or token generation fails
     */
    @Override
    public AuthResponse authenticate(UserLoginRequest loginRequest) throws Exception {

        UserEntity user =  (UserEntity) authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())).getPrincipal();
        return
                AuthResponse.builder()
                        .accessToken(jwtService.getAccessToken(user,new LoginContext()))
                        .refreshToken(jwtService.getRefreshToken(user))
                        .user(user)
                        .expireIn(10000L)
                        .build();
    }

    /**
     * Generates a new access token using a valid refresh token.
     *
     * @param refreshToken existing refresh token
     * @return AuthResponse with refreshed access token
     */
    @Override
    public AuthResponse refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public AuthResponse switchLoginContext(LoginContext loginContext) throws Exception {

        UserEntity userEntity = userCrudService.getUserById(UserContextHolder.getAuthContext().getUserId());
        loginContext.setPermission(
                userRoleMappingCrudService.getListOfPermissionForUser(UserContextHolder.getAuthContext().getUserId(),
                        loginContext.getBistroId(),loginContext.getBranchId(),
                        loginContext.getRoleId())
        );

        return
                AuthResponse.builder()
                        .accessToken(jwtService.getAccessToken(userEntity,loginContext))
                        .user(userEntity)
                        .expireIn(10000L)
                        .build();

    }
}

package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.contexts.AuthContext;
import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.AuthResponse;
import com.bistral.app.bistral_auth_service.dtos.LoginContext;
import com.bistral.app.bistral_auth_service.dtos.UserLoginContextDto;
import com.bistral.app.bistral_auth_service.dtos.UserLoginRequest;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.service.interfaces.AuthService;
import com.bistral.app.bistral_auth_service.service.interfaces.JwtService;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantUserCrudService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


/**
 * Service implementation responsible for handling authentication workflows.
 * <p>
 * Authenticates tenantUser credentials using Spring Security's AuthenticationManager
 * and generates JWT access and refresh tokens via JwtService.
 */
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {


    private static final Logger log =
            LoggerFactory.getLogger(AuthServiceImpl.class);
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRoleMappingCrudServiceImpl userRoleMappingCrudService;
    private final TenantUserCrudService tenantUserCrudService;

    /**
     * Authenticates tenantUser credentials and returns JWT tokens.
     *
     * @param loginRequest contains username and password
     * @return AuthResponse containing access token, refresh token, and tenantUser details
     * @throws Exception if authentication or token generation fails
     */
    @Override
    public AuthResponse authenticate(UserLoginRequest loginRequest) throws Exception {
        
        TenantUser tenantUser = (TenantUser) authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())).getPrincipal();
        return
                AuthResponse.builder()
                        .accessToken(jwtService.getAccessToken(tenantUser, new LoginContext()))
                        .refreshToken(jwtService.getRefreshToken(tenantUser))
                        .userContext(UserLoginContextDto.buildUserLoginContext(tenantUser, null))
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
    public AuthResponse refreshToken(String refreshToken) throws Exception {
        AuthContext authContext = UserContextHolder.getAuthContext();
        LoginContext loginContext = LoginContext
                .builder()
                .permission(authContext.getPermissions().stream().toList())
                .bistroId(authContext.getBistroId())
                .branchId(authContext.getBranchId())
                .roleId(authContext.getRoleId())
                .build();
        TenantUser tenantUser = tenantUserCrudService.getTenantUserByUserId(authContext.getUserId());
        return AuthResponse
                .builder()
                .userContext(UserLoginContextDto.buildUserLoginContext(tenantUser, loginContext))
                .accessToken(jwtService.getAccessToken(tenantUser, loginContext))
                .refreshToken(jwtService.getRefreshToken(tenantUser))
                .build();

    }

    @Override
    public AuthResponse switchLoginContext(LoginContext loginContext) throws Exception {

        TenantUser tenantUser = tenantUserCrudService.getTenantUserByUserId(UserContextHolder.getAuthContext().getUserId());
        loginContext.setPermission(
                userRoleMappingCrudService.getListOfPermissionForUser(UserContextHolder.getAuthContext().getUserId(),
                        loginContext.getBistroId(), loginContext.getBranchId(),
                        loginContext.getRoleId())
        );
        return
                AuthResponse.builder()
                        .accessToken(jwtService.getAccessToken(tenantUser, loginContext))
                        .userContext(UserLoginContextDto.buildUserLoginContext(tenantUser, loginContext))
                        .expireIn(10000L)
                        .build();

    }
}

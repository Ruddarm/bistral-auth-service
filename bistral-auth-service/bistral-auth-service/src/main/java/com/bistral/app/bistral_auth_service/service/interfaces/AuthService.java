package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.AuthResponse;
import com.bistral.app.bistral_auth_service.dtos.LoginContext;
import com.bistral.app.bistral_auth_service.dtos.UserLoginRequest;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Service interface responsible for handling authentication workflows.
 * Provides operations for user login, token generation, and token refresh.
 * Delegates JWT creation and validation to JwtService.
 *
 */
public interface AuthService {

    /*
     * Authenticates user credentials and returns access and refresh tokens.
     *
     * @param request contains username and password
     * @return authentication response with JWT tokens
     */

    public AuthResponse authenticate(UserLoginRequest userLoginRequest) throws Exception;

    /**
     * * Generates a new access token using a valid refresh token.
     * *
     * * @param refreshToken the refresh token
     * * @return new authentication response with fresh access token
     */
    public AuthResponse refreshToken(String AccessToken);

    /**
     *
     * @param loginContext
     * @return
     */
    public AuthResponse switchLoginContext(LoginContext loginContext) throws Exception;

}

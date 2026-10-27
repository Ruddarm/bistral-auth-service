package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.LoginContext;
import com.bistral.app.bistral_auth_service.entity.Users;
import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for generating JWT tokens.
 *
 * Defines contract for creating access and refresh tokens
 * for authenticated users.
 */
public interface JwtService {



    /**
     * Generates a short-lived access token for the given user.
     *
     * @param user the authenticated user
     * @return JWT access token
     */
    public String getAccessToken(Users user, LoginContext loginContext) throws Exception;

    /**
     * Generates a long-lived refresh token for the given user.
     *
     * @param user the authenticated user
     * @return JWT refresh token
     */
    public String getRefreshToken(Users user) throws Exception;

    /**
     *
     *
     */
    public  boolean isTokenValid(String token , Users users);

    /**
     *
     */
    public String getUserName(String token);

    /**
     * @param token
     * @return
     */
    public Claims getClaims(String token);

    /**
     *
     *
     * @param token
     * @return
     */
    public UUID  getUserId(String token);

    /**
     *
     * @param token
     * @return
     */
    public UUID getBistroId(String token);

    /**
     *
     * @param token
     * @return
     */
    public UUID getBranchId(String token);


    /**
     *
     * @param token
     * @return
     */
    public List<String> getPermissions(String token);

}

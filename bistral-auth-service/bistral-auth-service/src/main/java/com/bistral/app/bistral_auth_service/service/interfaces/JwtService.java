package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.LoginContext;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
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
     * Generates a short-lived access token for the given tenantUser.
     *
     * @param tenantUser the authenticated tenantUser
     * @return JWT access token
     */
    public String getAccessToken(TenantUser tenantUser, LoginContext loginContext) throws Exception;

    /**
     * Generates a long-lived refresh token for the given tenantUser.
     *
     * @param tenantUser the authenticated tenantUser
     * @return JWT refresh token
     */
    public String getRefreshToken(TenantUser tenantUser) throws Exception;

    /**
     *
     *
     */
    public  boolean isTokenValid(String token , TenantUser tenantUser);

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

package com.bistral.app.bistral_auth_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 *
 */
@AllArgsConstructor
@Builder
@Data
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private UserLoginContextDto userContext;
    private Long expireIn;
}

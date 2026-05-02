package com.bistral.app.bistral_auth_service.dtos;

import com.bistral.app.bistral_auth_service.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

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

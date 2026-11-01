package com.bistral.app.bistral_auth_service.dtos;

import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginContextDto {

    private UUID userId;
    private String userName;
    private String userEmail;
    private UserType userType;
    private LoginContext loginContext;

    public static UserLoginContextDto buildUserLoginContext(TenantUser tenantUser, LoginContext loginContext) {
        return UserLoginContextDto
                .builder()
                .userId(tenantUser.getUserId())
                .userName(tenantUser.getUsername())
                .userEmail(tenantUser.getUserEmail())
                .loginContext(loginContext)
                .build();
    }

}

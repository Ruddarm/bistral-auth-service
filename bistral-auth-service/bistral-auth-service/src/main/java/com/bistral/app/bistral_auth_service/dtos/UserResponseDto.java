package com.bistral.app.bistral_auth_service.dtos;


import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object representing {@link  TenantUser} details exposed in API responses.
 *
 * <p>
 * This DTO contains only non-sensitive and required tenantUser information
 * that is safe to return to clients. It excludes confidential fields
 * such as password or internal audit data.
 * </p>
 *
 * <p>
 * Typically used in tenantUser-related API responses such as fetching tenantUser details.
 * </p>
 */
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserResponseDto {

    private UUID userId;
    private String username;
    private String userEmail;
    private UserType userTypeEnum;

}

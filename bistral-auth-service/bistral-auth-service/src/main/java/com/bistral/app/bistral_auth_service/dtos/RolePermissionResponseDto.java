package com.bistral.app.bistral_auth_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.UUID;

/**
 *
 */
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RolePermissionResponseDto {

    private UUID resourceId;
    private String resourceName;
    private UUID actionId;
    private String actionName;

}

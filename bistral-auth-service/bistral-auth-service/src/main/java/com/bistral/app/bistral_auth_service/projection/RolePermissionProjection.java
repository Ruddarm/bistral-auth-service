package com.bistral.app.bistral_auth_service.projection;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


public interface RolePermissionProjection {
    UUID getRoleId();
    String getRoleName();
    UUID getResourceId();
    String getResourceName();
    UUID getActionId();
    String getActionName();
}

package com.bistral.app.bistral_auth_service.dtos;


import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Builder
@Data
public class UserRoleResponseDto {
    private String rollName;
    private UUID roleId;
    private UUID branchId;
}


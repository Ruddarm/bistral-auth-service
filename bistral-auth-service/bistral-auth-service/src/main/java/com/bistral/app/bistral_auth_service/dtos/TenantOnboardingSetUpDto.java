package com.bistral.app.bistral_auth_service.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class TenantOnboardingSetUpDto {

    @NotNull
    private UUID tenantId;

    @NotNull
    private UUID bistroId;

    @NotNull
    private UUID userId;
}

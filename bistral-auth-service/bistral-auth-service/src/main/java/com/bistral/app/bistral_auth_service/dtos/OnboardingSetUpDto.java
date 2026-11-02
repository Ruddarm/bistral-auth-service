package com.bistral.app.bistral_auth_service.dtos;


import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class OnboardingSetUpDto {

    @NotNull
    private UUID tenantId;

    @NotNull
    private UUID bistroId;

    @NotNull
    private UUID userId;
}

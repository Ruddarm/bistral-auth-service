package com.bistral.app.bistral_auth_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OnboardTenantUserDto {

    @NotBlank
    @Email
    private String userEmail;

    @NotNull
    private UUID tenantId;
}

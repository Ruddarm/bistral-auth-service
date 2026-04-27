package com.bistral.app.bistral_auth_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ResourceRequestDto {

    @NotNull
    @NotBlank
    private String resourceName;

}

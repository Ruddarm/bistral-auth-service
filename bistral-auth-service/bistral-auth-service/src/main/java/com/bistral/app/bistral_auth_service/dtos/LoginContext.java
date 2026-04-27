package com.bistral.app.bistral_auth_service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginContext {

    @NotNull
    private UUID bistroId;
    @NotNull
    private UUID branchId;
    @NotNull
    private UUID roleId;
    private List<String> permission;

}

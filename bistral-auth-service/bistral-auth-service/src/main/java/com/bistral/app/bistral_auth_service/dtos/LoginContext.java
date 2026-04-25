package com.bistral.app.bistral_auth_service.dtos;

import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginContext {

    private UUID bistroId;
    private UUID branchId;
    private UUID roleId;
    private List<String> permission;

}

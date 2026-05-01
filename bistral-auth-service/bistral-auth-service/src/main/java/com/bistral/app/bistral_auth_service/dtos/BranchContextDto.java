package com.bistral.app.bistral_auth_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchContextDto {

    private UUID branchId;
    private String branchName;
    private List<RoleResponseDto> roleResponseDtoList;

}

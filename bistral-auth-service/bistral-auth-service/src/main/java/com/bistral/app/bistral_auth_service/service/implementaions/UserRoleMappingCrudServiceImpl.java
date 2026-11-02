package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.dtos.*;
import com.bistral.app.bistral_auth_service.openfeignclients.BistroFeignClient;
import com.bistral.app.bistral_auth_service.repository.TenantRoleRepository;
import com.bistral.app.bistral_auth_service.repository.TenantUserRepository;
import com.bistral.app.bistral_auth_service.repository.UserRoleMappingRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.RoleUserMappingCrudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserRoleMappingCrudServiceImpl implements RoleUserMappingCrudService {

    private final UserRoleMappingRepository userRoleMappingRepository;
    private final TenantUserRepository userRepository;
    private final TenantRoleRepository tenantRoleRepository;
    private final BistroFeignClient bistroFeignClient;

    @Override
    public Boolean assignRole(UserRoleMappingRequestDto roleMappingRequestDto) {
        UUID userId = roleMappingRequestDto.getUserId();
//        TenantUser tenantUser = TenantUser.builder()
//                .userId(userId)
//                .build();
//        UUID bistroId = roleMappingRequestDto.getBistroId();
//        List<TenantUserRoleMapping> roleMappingEntities =
//                roleMappingRequestDto.getRoleAssignmentDtoList()
//                        .stream().map((assignRole) -> {
//                            return TenantUserRoleMapping.builder()
//                                    .tenantUser(tenantUser)
//                                    .bistroId(bistroId)
//                                    .createdBy(tenantUser)
//                                    .tenantRole(TenantRole.builder()
//                                    .build();
//                        }).toList();
//        userRoleMappingRepository.saveAll(roleMappingEntities);
        return Boolean.TRUE;
    }

    @Override
    public List<BistroContextDto> getRolesOfUser() {
//        List<TenantUserRoleMapping> roleMappingEntities =
//                userRoleMappingRepository.findRolesOfUserByUserId(UserContextHolder
//                        .getAuthContext().getUserId());
//        Map<UUID, Map<UUID, List<TenantRoleResponseDto>>> roleMap = new HashMap<>();
//        Set<UUID> bistroIds = new HashSet<>();
//        Set<UUID> branchIds = new HashSet<>();
//        roleMappingEntities.forEach(roleMapping -> {
//            bistroIds.add(roleMapping.getBistroId());
//            branchIds.add(roleMapping.getBranchId());
//        });
//        List<BistroContextDto> bistroContext =
//                bistroFeignClient
//                        .getBistroContext(BistroContextRequestDto
//                                .builder().bistroIds(bistroIds.stream().toList())
//                                .branchIds(branchIds.stream().toList()).build());
//        Map<UUID, BistroContextDto> bistroContextDtoMap = bistroContext.stream()
//                .collect(Collectors.toMap(
//                        BistroContextDto::getBistroId,
//                        bistro -> bistro
//                ));
//        Map<UUID, BranchContextDto> branchContextDtoMap = new HashMap<>();
//        bistroContext.forEach((bistro) ->
//                bistro.getBranches()
//                        .forEach((branch) ->
//                                branchContextDtoMap.putIfAbsent(branch.getBranchId(), branch)
//                        )
//
//        );
//        roleMappingEntities
//                .forEach((roleMapping) -> {
//                    if (bistroContextDtoMap.containsKey(roleMapping.getBistroId())) {
//                        BistroContextDto bistroContextDto = bistroContextDtoMap.get(roleMapping.getBistroId());
//                        if (bistroContextDto != null) {
//                            BranchContextDto branchContextDto = branchContextDtoMap.get(roleMapping.getBranchId());
//                            if (branchContextDto.getRoles() == null) {
//                                branchContextDto.setRoles(new ArrayList<>());
//                            }
//                            branchContextDto.getRoles()
//                                    .add(TenantRoleResponseDto
//                                            .builder()
//                                            .userRoleId(roleMapping.getTenantRole().getUserRoleId())
//                                            .roleName(roleMapping.getTenantRole().getRoleName())
//                                            .build());
//                        }
//                    }
//                });
//        return bistroContextDtoMap.values().stream().toList();
        return  null;
    }

    @Override
    public List<String> getListOfPermissionForUser(UUID userId, UUID bistroId, UUID branchId, UUID roleId) {
//        return userRoleMappingRepository
//                .findPermissionByContext(userId, bistroId, branchId, roleId);
        return null;
    }


}

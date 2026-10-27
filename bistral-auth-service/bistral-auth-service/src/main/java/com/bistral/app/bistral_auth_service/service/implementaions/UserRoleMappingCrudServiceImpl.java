package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.*;
import com.bistral.app.bistral_auth_service.entity.Roles;
import com.bistral.app.bistral_auth_service.entity.UserRoleMapping;
import com.bistral.app.bistral_auth_service.entity.Users;
import com.bistral.app.bistral_auth_service.openfeignclients.BistroFeignClient;
import com.bistral.app.bistral_auth_service.repository.RoleRepository;
import com.bistral.app.bistral_auth_service.repository.UserRepository;
import com.bistral.app.bistral_auth_service.repository.UserRoleMappingRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.RoleUserMappingCrudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserRoleMappingCrudServiceImpl implements RoleUserMappingCrudService {

    private final UserRoleMappingRepository userRoleMappingRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BistroFeignClient bistroFeignClient;

    @Override
    public Boolean assignRole(UserRoleMappingRequestDto roleMappingRequestDto) {
        UUID userId = roleMappingRequestDto.getUserId();
        Users user = Users.builder()
                .userId(userId)
                .build();
        UUID bistroId = roleMappingRequestDto.getBistroId();
        List<UserRoleMapping> roleMappingEntities =
                roleMappingRequestDto.getRoleAssignmentDtoList()
                        .stream().map((assignRole) -> {
                            return UserRoleMapping.builder()
                                    .user(user)
                                    .bistroId(bistroId)
                                    .createdBy(user)
                                    .role(Roles.builder()
                                            .userRoleId(assignRole.getRoleId()).build())
                                    .branchId(assignRole.getBranchId())
                                    .build();
                        }).toList();
        userRoleMappingRepository.saveAll(roleMappingEntities);
        return Boolean.TRUE;
    }

    @Override
    public List<BistroContextDto> getRolesOfUser() {
        List<UserRoleMapping> roleMappingEntities =
                userRoleMappingRepository.findRolesOfUserByUserId(UserContextHolder
                        .getAuthContext().getUserId());
        Map<UUID, Map<UUID, List<RoleResponseDto>>> roleMap = new HashMap<>();
        Set<UUID> bistroIds = new HashSet<>();
        Set<UUID> branchIds = new HashSet<>();
        roleMappingEntities.forEach(roleMapping -> {
            bistroIds.add(roleMapping.getBistroId());
            branchIds.add(roleMapping.getBranchId());
        });
        List<BistroContextDto> bistroContext =
                bistroFeignClient
                        .getBistroContext(BistroContextRequestDto
                                .builder().bistroIds(bistroIds.stream().toList())
                                .branchIds(branchIds.stream().toList()).build());
        Map<UUID, BistroContextDto> bistroContextDtoMap = bistroContext.stream()
                .collect(Collectors.toMap(
                        BistroContextDto::getBistroId,
                        bistro -> bistro
                ));
        Map<UUID, BranchContextDto> branchContextDtoMap = new HashMap<>();
        bistroContext.forEach((bistro) ->
                bistro.getBranches()
                        .forEach((branch) ->
                                branchContextDtoMap.putIfAbsent(branch.getBranchId(), branch)
                        )

        );
        roleMappingEntities
                .forEach((roleMapping) -> {
                    if (bistroContextDtoMap.containsKey(roleMapping.getBistroId())) {
                        BistroContextDto bistroContextDto = bistroContextDtoMap.get(roleMapping.getBistroId());
                        if (bistroContextDto != null) {
                            BranchContextDto branchContextDto = branchContextDtoMap.get(roleMapping.getBranchId());
                            if (branchContextDto.getRoles() == null) {
                                branchContextDto.setRoles(new ArrayList<>());
                            }
                            branchContextDto.getRoles()
                                    .add(RoleResponseDto
                                            .builder()
                                            .userRoleId(roleMapping.getRole().getUserRoleId())
                                            .roleName(roleMapping.getRole().getRoleName())
                                            .build());
                        }
                    }
                });
        return bistroContextDtoMap.values().stream().toList();
    }

    @Override
    public List<String> getListOfPermissionForUser(UUID userId, UUID bistroId, UUID branchId, UUID roleId) {
        return userRoleMappingRepository
                .findPermissionByContext(userId, bistroId, branchId, roleId);
    }


}

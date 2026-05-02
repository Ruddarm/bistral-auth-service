package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.*;
import com.bistral.app.bistral_auth_service.entity.RoleEntity;
import com.bistral.app.bistral_auth_service.entity.UserEntity;
import com.bistral.app.bistral_auth_service.entity.UserRoleMappingEntity;
import com.bistral.app.bistral_auth_service.openfeignclients.BistroFeignClient;
import com.bistral.app.bistral_auth_service.repository.RoleRepository;
import com.bistral.app.bistral_auth_service.repository.UserRepository;
import com.bistral.app.bistral_auth_service.repository.UserRoleMappingRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.RoleUserMappingCrudService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.apache.commons.text.translate.UnicodeUnescaper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class UserRoleMappingCrudServiceImpl implements RoleUserMappingCrudService {

    private final UserRoleMappingRepository userRoleMappingRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BistroFeignClient bistroFeignClient;

    @Override
    public Boolean assignRole(UserRoleMappingRequestDto roleMappingRequestDto) {
        UUID userId = roleMappingRequestDto.getUserId();
        UserEntity user = UserEntity.builder()
                .userId(userId)
                .build();
        UUID bistroId = roleMappingRequestDto.getBistroId();
        List<UserRoleMappingEntity> roleMappingEntities =
                roleMappingRequestDto.getRoleAssignmentDtoList()
                        .stream().map((assignRole) -> {
                            return UserRoleMappingEntity.builder()
                                    .user(user)
                                    .bistroId(bistroId)
                                    .createdBy(user)
                                    .role(RoleEntity.builder()
                                            .userRoleId(assignRole.getRoleId()).build())
                                    .branchId(assignRole.getBranchId())
                                    .build();
                        }).toList();
        userRoleMappingRepository.saveAll(roleMappingEntities);
        return Boolean.TRUE;
    }

    @Override
    public List<BistroContextDto> getRolesOfUser() {
        List<UserRoleMappingEntity> roleMappingEntities =
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

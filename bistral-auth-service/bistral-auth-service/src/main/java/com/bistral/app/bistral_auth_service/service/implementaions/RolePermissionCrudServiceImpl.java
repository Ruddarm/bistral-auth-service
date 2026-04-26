package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.RolePermissionRequestDto;
import com.bistral.app.bistral_auth_service.dtos.RoleResponseDto;
import com.bistral.app.bistral_auth_service.entity.*;
import com.bistral.app.bistral_auth_service.mapper.RoleMapper;
import com.bistral.app.bistral_auth_service.repository.RolePermissionRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.RoleCrudService;
import com.bistral.app.bistral_auth_service.service.interfaces.RolePermissionCrudService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 *
 *
 */
@AllArgsConstructor
@Service
public class RolePermissionCrudServiceImpl implements RolePermissionCrudService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleCrudService roleCrudService;
    private final RoleMapper roleMapper;

    /**
     *
     * @param rolePermissionRequests
     * @return
     */
    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public RoleResponseDto assignPermissionToRole(UUID roleId, List<RolePermissionRequestDto> rolePermissionRequests) {

        RoleEntity roleEntity = roleCrudService
                .getRoleByIdAndBistroId(roleId);
        UserEntity createdBy = UserEntity.builder().userId(UserContextHolder
                .getAuthContext().getUserId()).build();
        rolePermissionRepository.deleteByRoleId(roleId);
        List<RolePermissionEntity> permissionEntities
                = rolePermissionRequests
                .stream()
                .filter(RolePermissionRequestDto::getIsAllowed).map(
                        (req) ->
                                RolePermissionEntity.
                                        builder()
                                        .role(roleEntity)
                                        .resource(ResourceEntity.builder().resourceId(req.getResourceId()).build())
                                        .actionEntity(ActionEntity.builder().actionId(req.getActionId()).build())
                                        .createdBy(createdBy)
                                        .build()
                ).toList();
        rolePermissionRepository
                .saveAll(permissionEntities);
        return
                roleMapper.toRoleResponseWithPermission(
                        roleCrudService.getRoleByRoleIdBistroIdWithPermission(roleId)
                );
    }

}

package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.dtos.RolePermissionRequestDto;
import com.bistral.app.bistral_auth_service.dtos.TenantRoleResponseDto;
import com.bistral.app.bistral_auth_service.mapper.RoleMapper;
import com.bistral.app.bistral_auth_service.repository.RolePermissionRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantRoleCrudService;
import com.bistral.app.bistral_auth_service.service.interfaces.RolePermissionCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final TenantRoleCrudService tenantRoleCrudService;
    private final RoleMapper roleMapper;

    /**
     *
     * @param rolePermissionRequests
     * @return
     */
    @Transactional
    @Override
    public TenantRoleResponseDto assignPermissionToRole(UUID roleId, List<RolePermissionRequestDto> rolePermissionRequests) {

//        TenantRole tenantRole = tenantRoleCrudService
//                .getRoleByIdAndBistroId(roleId);
//        TenantUser createdBy = TenantUser.builder().userId(UserContextHolder
//                .getAuthContext().getUserId()).build();
//        rolePermissionRepository.deleteByRoleId(roleId);
//        List<TenantRolePermission> permissionEntities
//                = rolePermissionRequests
//                .stream()
//                .filter(RolePermissionRequestDto::getIsAllowed).map(
//                        (req) ->
//                                TenantRolePermission.
//                                        builder()
//                                        .tenantRole(tenantRole)
//                                        .createdBy(createdBy)
//                                        .build()
//                ).toList();
//        rolePermissionRepository
//                .saveAll(permissionEntities);
//        return
//                roleMapper.toRoleResponseWithPermission(
//                        tenantRoleCrudService.getRoleByRoleIdBistroIdWithPermission(roleId)
//                );
        return null;
    }

}

package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.annotation.HasPermission;
import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.PageResponse;
import com.bistral.app.bistral_auth_service.dtos.RoleFilterRequest;
import com.bistral.app.bistral_auth_service.dtos.TenantRoleRequestDto;
import com.bistral.app.bistral_auth_service.dtos.TenantRoleResponseDto;
import com.bistral.app.bistral_auth_service.entity.ResourceActionMapping;
import com.bistral.app.bistral_auth_service.entity.TenantRole;
import com.bistral.app.bistral_auth_service.entity.TenantRolePermission;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.exceptions.ResourceNotFoundExceptions;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.mapper.RoleMapper;
import com.bistral.app.bistral_auth_service.projection.RolePermissionProjection;
import com.bistral.app.bistral_auth_service.repository.ResourceActionsMappingRepository;
import com.bistral.app.bistral_auth_service.repository.TenantRoleRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.ResourceActionsMappingCrudService;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantRoleCrudService;
import com.bistral.app.bistral_auth_service.utility.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TenantRoleCrudServiceImpl implements TenantRoleCrudService {

    private final TenantRoleRepository tenantRoleRepository;
    private final RoleMapper roleMapper;
    private final ResourceActionsMappingCrudService resourceActionsMappingCrudService;
    private TenantUserCrudServiceImpl tenantUserCrudService;


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public TenantRoleResponseDto createRole(TenantRoleRequestDto tenantRoleRequestDto) throws UserNotFoundException {
        TenantRole tenantRole = roleMapper.toRoleEntity(tenantRoleRequestDto);
        TenantRole parentRole = this.getRoleByIdAndBistroId(tenantRoleRequestDto.getParentRoleId());
        tenantRole.setParentRole(parentRole);
        tenantRole.setCreatedBy(TenantUser.builder()
                .userId(
                        tenantUserCrudService
                                .getTenantUserByUserId(UserContextHolder.getAuthContext()
                                        .getUserId()).getUserId()
                )
                .build()
        );
        tenantRoleRequestDto.getTenantRolePermissionMappings()
                .forEach(permissionMapping -> {
                    tenantRole.addTenantRolePermission(
                            TenantRolePermission
                                    .builder()
                                    .resourceActionMapping(ResourceActionMapping.builder().resourceActionMappingId(permissionMapping.getResourceActionMappingId()).build())
                                    .isAllowed(permissionMapping.isAllowed())
                                    .build()
                    );
                });
        tenantRole.setBistroId(UserContextHolder.getAuthContext().getBistroId());
        return
                roleMapper.toRoleResponse(tenantRoleRepository.save(tenantRole));
    }

    @Transactional(rollbackFor = Exception.class)
    @HasPermission("Tenant:Onboard")
    public TenantRole createRootRole(UUID tenantId, UUID bistroId, TenantUser tenantUser) {
        TenantRole tenantRole = TenantRole
                .builder()
                .tenantId(tenantId)
                .roleName("Owner")
                .isRootRole(true)
                .createdBy(tenantUser)
                .bistroId(bistroId)
                .build();
        List<ResourceActionMapping> resourceActionMappings = resourceActionsMappingCrudService
                .getAllNonSystemSpecificResourceActionMappings();
        TenantRole finalTenantRole = tenantRole;
        for (ResourceActionMapping resourceActionMapping : resourceActionMappings) {
            finalTenantRole.addTenantRolePermission(
                    TenantRolePermission
                            .builder()
                            .resourceActionMapping(resourceActionMapping)
                            .isAllowed(true)
                            .build()
            );
        }
        tenantRole = tenantRoleRepository.save(tenantRole);
        return tenantRole;
    }


    @Override
    public TenantRole getRoleByIdAndBistroId(UUID roleId) {
        ValidationUtil.notNull(roleId, "Role Id Can't be null");
        return
                tenantRoleRepository.findByTenantRoleIdAndBistroId(
                        roleId,
                        UserContextHolder.getAuthContext().getBistroId()
                ).orElseThrow(() -> new ResourceNotFoundExceptions("Role Not found for given Id", roleId.toString(), "tenantRole"))
                ;

    }

    @Override
    public PageResponse<TenantRoleResponseDto> getListOfRoles(RoleFilterRequest filterRequest, Integer page, Integer size) {
        return null;
    }


    @Override
    public List<RolePermissionProjection> getRoleByRoleIdBistroIdWithPermission(UUID roleId) {
//        return tenantRoleRepository
//                .findRoleByIdAndBistroIdWithPermissions(roleId,UserContextHolder
//                        .getAuthContext().getBistroId());
        return null;
    }

}

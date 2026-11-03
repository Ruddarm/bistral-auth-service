package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.dtos.*;
import com.bistral.app.bistral_auth_service.openfeignclients.BistroFeignClient;
import com.bistral.app.bistral_auth_service.repository.TenantRoleRepository;
import com.bistral.app.bistral_auth_service.repository.TenantUserRepository;
import com.bistral.app.bistral_auth_service.repository.TenantUserRoleMappingRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.RoleUserMappingCrudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class TenantUserRoleMappingCrudServiceImpl implements RoleUserMappingCrudService {

    private final TenantUserRoleMappingRepository tenantUserRoleMappingRepository;
    private final TenantUserRepository userRepository;
    private final TenantRoleRepository tenantRoleRepository;
    private final BistroFeignClient bistroFeignClient;

    @Override
    public Boolean assignRole(UserRoleMappingRequestDto roleMappingRequestDto) {

        return Boolean.TRUE;
    }

    public

    @Override
    public List<BistroContextDto> getRolesOfUser() {

        return  null;
    }

    @Override
    public List<String> getListOfPermissionForUser(UUID userId, UUID bistroId, UUID branchId, UUID roleId) {

        return null;
    }


}

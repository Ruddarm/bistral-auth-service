package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.dtos.PageResponse;
import com.bistral.app.bistral_auth_service.dtos.RoleFilterRequest;
import com.bistral.app.bistral_auth_service.dtos.RoleRequestDto;
import com.bistral.app.bistral_auth_service.dtos.RoleResponseDto;
import com.bistral.app.bistral_auth_service.entity.RoleEntity;
import com.bistral.app.bistral_auth_service.entity.UserEntity;
import com.bistral.app.bistral_auth_service.exceptions.ResourceNotFoundExceptions;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.mapper.RoleMapper;
import com.bistral.app.bistral_auth_service.repository.RoleRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.RoleCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleCrudServiceImpl implements RoleCrudService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    private UserCrudServiceImpl userCrudService;

    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) throws UserNotFoundException {

        RoleEntity roleEntity = roleMapper.toRoleEntity(roleRequestDto);
        roleEntity.setCreatedBy(UserEntity.builder()
                .userId(
                        userCrudService.getUserById(roleRequestDto.getBistroId()).getUserId()
                )
                .build()
        );
        return
                roleMapper.toRoleResponse(roleRepository.save(roleEntity));

    }

    @Override
    public RoleResponseDto getRoleById(UUID roleId) {
        return roleMapper
                .toRoleResponse(
                        roleRepository.findById(roleId)
                                .orElseThrow(() -> new ResourceNotFoundExceptions("Role Not Found with given Id", roleId.toString(), "Role"))
                );

    }

    @Override
    public PageResponse<RoleResponseDto> getListOfRoles(RoleFilterRequest filterRequest, Integer page, Integer size) {
        return null;
    }
}

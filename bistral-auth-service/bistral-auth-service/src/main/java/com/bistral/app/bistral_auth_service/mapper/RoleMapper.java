package com.bistral.app.bistral_auth_service.mapper;

import com.bistral.app.bistral_auth_service.dtos.RolePermissionResponseDto;
import com.bistral.app.bistral_auth_service.dtos.RoleRequestDto;
import com.bistral.app.bistral_auth_service.dtos.RoleResponseDto;
import com.bistral.app.bistral_auth_service.dtos.UserResponseDto;
import com.bistral.app.bistral_auth_service.entity.RoleEntity;
import com.bistral.app.bistral_auth_service.projection.RolePermissionProjection;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    RoleResponseDto toRoleResponse(RoleEntity roleEntity);


    RoleEntity toRoleEntity(RoleRequestDto roleRequestDto);


    default RoleResponseDto toRoleResponseWithPermission(List<RolePermissionProjection> rolePermissionProjection) {
        if (rolePermissionProjection == null || rolePermissionProjection.isEmpty()) return null;
        UUID roleId = rolePermissionProjection.get(0).getRoleId();
        String roleName = rolePermissionProjection.get(0).getRoleName();
        return RoleResponseDto
                .builder()
                .userRoleId(roleId)
                .roleName(roleName)
                .rolePermissionResponses(
                        rolePermissionProjection.stream()
                                .map(this::mapPermission
                                ).toList()
                )
                .build();
    }

    default RolePermissionResponseDto mapPermission(RolePermissionProjection projection) {
        return RolePermissionResponseDto.builder()
                .actionName(projection.getActionName())
                .actionId(projection.getActionId())
                .resourceId(projection.getResourceId())
                .resourceName(projection.getResourceName())
                .build();
    }
}

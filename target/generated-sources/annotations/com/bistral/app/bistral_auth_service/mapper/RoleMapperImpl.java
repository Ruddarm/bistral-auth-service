package com.bistral.app.bistral_auth_service.mapper;

import com.bistral.app.bistral_auth_service.dtos.RoleRequestDto;
import com.bistral.app.bistral_auth_service.dtos.RoleResponseDto;
import com.bistral.app.bistral_auth_service.entity.RoleEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-21T16:55:24+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleResponseDto toRoleResponse(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleResponseDto.RoleResponseDtoBuilder roleResponseDto = RoleResponseDto.builder();

        roleResponseDto.userRoleId( roleEntity.getUserRoleId() );
        roleResponseDto.roleName( roleEntity.getRoleName() );

        return roleResponseDto.build();
    }

    @Override
    public RoleEntity toRoleEntity(RoleRequestDto roleRequestDto) {
        if ( roleRequestDto == null ) {
            return null;
        }

        RoleEntity.RoleEntityBuilder roleEntity = RoleEntity.builder();

        roleEntity.roleName( roleRequestDto.getRoleName() );
        roleEntity.bistroId( roleRequestDto.getBistroId() );

        return roleEntity.build();
    }
}

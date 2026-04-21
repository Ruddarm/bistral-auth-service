package com.bistral.app.bistral_auth_service.mapper;

import com.bistral.app.bistral_auth_service.dtos.RoleAssignmentDto;
import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingRequestDto;
import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingResponseDto;
import com.bistral.app.bistral_auth_service.entity.UserRoleMappingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" ,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMappingMapper {

    UserRoleMappingEntity toRoleMappingEntity(UserRoleMappingRequestDto userRoleMappingRequestDto);
    UserRoleMappingResponseDto toUserRoleMappingResponseDto(UserRoleMappingEntity userRoleMappingEntity);

}

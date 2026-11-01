package com.bistral.app.bistral_auth_service.mapper;

import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingRequestDto;
import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingResponseDto;
import com.bistral.app.bistral_auth_service.entity.TenantUserRoleMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" ,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMappingMapper {

    TenantUserRoleMapping toRoleMappingEntity(UserRoleMappingRequestDto userRoleMappingRequestDto);
    UserRoleMappingResponseDto toUserRoleMappingResponseDto(TenantUserRoleMapping userRoleMapping);

}

package com.bistral.app.bistral_auth_service.mapper;

import com.bistral.app.bistral_auth_service.dtos.TenantUserResponseDto;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" ,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    TenantUserResponseDto toUserResponseDto(TenantUser tenantUser);

}

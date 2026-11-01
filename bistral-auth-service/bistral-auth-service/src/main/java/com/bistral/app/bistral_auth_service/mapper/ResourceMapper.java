package com.bistral.app.bistral_auth_service.mapper;

import com.bistral.app.bistral_auth_service.dtos.ResourceRequestDto;
import com.bistral.app.bistral_auth_service.dtos.ResourceResponseDto;
import com.bistral.app.bistral_auth_service.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper {

    Resource toResourceEntity(ResourceRequestDto resourceRequestDto);

    ResourceResponseDto toResourceResponse(Resource resource);

}

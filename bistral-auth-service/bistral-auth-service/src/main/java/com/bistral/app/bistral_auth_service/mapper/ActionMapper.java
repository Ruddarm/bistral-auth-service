package com.bistral.app.bistral_auth_service.mapper;


import com.bistral.app.bistral_auth_service.dtos.ActionResponseDto;
import com.bistral.app.bistral_auth_service.entity.ActionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" ,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActionMapper {

    ActionResponseDto toActionResponse(ActionEntity actionEntity);
}

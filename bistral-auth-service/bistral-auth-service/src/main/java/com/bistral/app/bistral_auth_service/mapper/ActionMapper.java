package com.bistral.app.bistral_auth_service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" ,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActionMapper {

}

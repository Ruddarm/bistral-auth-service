package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.ResourceRequestDto;
import com.bistral.app.bistral_auth_service.dtos.ResourceResponseDto;
import com.bistral.app.bistral_auth_service.entity.Resource;

import java.util.List;
import java.util.UUID;

/**
 *
 */
public interface ResourceCrudService {


    public Resource createResource(ResourceRequestDto resourceRequestDto);

    public Resource findResourceById(UUID resourceId);

    public List<ResourceResponseDto> getResourcesList();



}

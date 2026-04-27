package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.ResourceRequestDto;
import com.bistral.app.bistral_auth_service.dtos.ResourceResponseDto;
import com.bistral.app.bistral_auth_service.entity.ResourceEntity;

import java.util.List;
import java.util.UUID;

/**
 *
 */
public interface ResourceCrudService {


    public ResourceEntity createResource(ResourceRequestDto resourceRequestDto);

    public ResourceEntity findResourceById(UUID resourceId);

    public List<ResourceEntity> getResourcesList();



}

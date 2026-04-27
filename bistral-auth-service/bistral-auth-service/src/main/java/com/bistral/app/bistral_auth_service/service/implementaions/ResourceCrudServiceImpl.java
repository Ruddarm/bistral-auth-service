package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.ResourceRequestDto;
import com.bistral.app.bistral_auth_service.entity.ResourceEntity;
import com.bistral.app.bistral_auth_service.entity.UserEntity;
import com.bistral.app.bistral_auth_service.exceptions.ResourceNotFoundExceptions;
import com.bistral.app.bistral_auth_service.repository.ResourceRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.ResourceCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ResourceCrudServiceImpl implements ResourceCrudService {

    private final ResourceRepository resourceRepository;

    @Override
    public ResourceEntity createResource(ResourceRequestDto resourceRequestDto) {

        ResourceEntity resourceEntity = ResourceEntity
                .builder()
                .resourceName(resourceRequestDto.getResourceName()
                        .toUpperCase())
                .createdBy(UserEntity.builder()
                        .userId(UserContextHolder
                                .getAuthContext()
                                .getUserId()).build())
                .build();
        return  resourceRepository.save(resourceEntity);
    }

    @Override
    public ResourceEntity findResourceById(UUID resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Resource not found with Id "+resourceId,
                        resourceId.toString(),"resource"));
    }

    @Override
    public List<ResourceEntity> getResourcesList() {
        return List.of();
    }
}

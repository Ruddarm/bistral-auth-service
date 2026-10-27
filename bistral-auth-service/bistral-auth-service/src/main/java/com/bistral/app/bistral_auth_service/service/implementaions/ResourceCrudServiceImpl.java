package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.ResourceRequestDto;
import com.bistral.app.bistral_auth_service.dtos.ResourceResponseDto;
import com.bistral.app.bistral_auth_service.entity.Resources;
import com.bistral.app.bistral_auth_service.entity.Users;
import com.bistral.app.bistral_auth_service.exceptions.ResourceNotFoundExceptions;
import com.bistral.app.bistral_auth_service.mapper.ResourceMapper;
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
    private final ResourceMapper resourceMapper;

    @Override
    public Resources createResource(ResourceRequestDto resourceRequestDto) {

        Resources resources = Resources
                .builder()
                .resourceName(resourceRequestDto.getResourceName()
                        .toUpperCase())
                .createdBy(Users.builder()
                        .userId(UserContextHolder
                                .getAuthContext()
                                .getUserId()).build())
                .build();
        return  resourceRepository.save(resources);
    }

    @Override
    public Resources findResourceById(UUID resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Resource not found with Id "+resourceId,
                        resourceId.toString(),"resource"));
    }

    @Override
    public List<ResourceResponseDto> getResourcesList() {
        return resourceRepository
                .findAll().stream()
                .map(resourceMapper::toResourceResponse).toList();
    }
}

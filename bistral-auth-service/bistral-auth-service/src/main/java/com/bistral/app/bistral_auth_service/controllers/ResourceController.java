package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import com.bistral.app.bistral_auth_service.dtos.ResourceRequestDto;
import com.bistral.app.bistral_auth_service.dtos.ResourceResponseDto;
import com.bistral.app.bistral_auth_service.mapper.ResourceMapper;
import com.bistral.app.bistral_auth_service.service.interfaces.ResourceCrudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceCrudService resourceCrudService;
    private final ResourceMapper resourceMapper;

    @GetMapping("/{resourceId}")
    public ResponseEntity<ApiResponse<ResourceResponseDto>> getResourceById(@PathVariable UUID resourceId) {
        return
                ResponseEntity.ok(
                        ApiResponse
                                .<ResourceResponseDto>builder()
                                .message("Resource Found Successfully")
                                .data(resourceMapper
                                        .toResourceResponse(resourceCrudService
                                                .findResourceById(resourceId)))
                                .build()
                );
    }


    @PostMapping()
    public ResponseEntity<ApiResponse<ResourceResponseDto>> createResource(@Valid @RequestBody ResourceRequestDto resourceRequestDto) {
        return new ResponseEntity<>(
                ApiResponse
                        .<ResourceResponseDto>builder()
                        .message("Resource Created Successfully")
                        .data(
                                resourceMapper.toResourceResponse(
                                        resourceCrudService.createResource(resourceRequestDto)
                                ))
                        .build(),
                HttpStatus.CREATED
        );
    }
}

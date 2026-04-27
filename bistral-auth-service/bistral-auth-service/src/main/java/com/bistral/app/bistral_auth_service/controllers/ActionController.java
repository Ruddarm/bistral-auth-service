package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.dtos.ActionRequestDto;
import com.bistral.app.bistral_auth_service.dtos.ActionResponseDto;
import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import com.bistral.app.bistral_auth_service.mapper.ActionMapper;
import com.bistral.app.bistral_auth_service.service.interfaces.ActionCrudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/actions")
public class ActionController {

    private final ActionCrudService actionCrudService;

    private final ActionMapper actionMapper;

    @GetMapping("/{actionId}")
    ResponseEntity<ApiResponse<ActionResponseDto>> getActionById(@PathVariable UUID actionId) {
        return ResponseEntity.ok(
                ApiResponse
                        .<ActionResponseDto>builder()
                        .message("action found Successfully")
                        .data(actionMapper
                                .toActionResponse(actionCrudService.getActionById(actionId)))
                        .build()

        );
    }

    @PostMapping()
    ResponseEntity<ApiResponse<ActionResponseDto>> createAction(@Valid @RequestBody ActionRequestDto actionRequestDto) {
        return new ResponseEntity(
                ApiResponse.<ActionResponseDto>
                                builder()
                        .message("Action Created Successfully")
                        .data(
                                actionCrudService.createAction(actionRequestDto)
                        ).build(),
                HttpStatus.CREATED
        );
    }


}



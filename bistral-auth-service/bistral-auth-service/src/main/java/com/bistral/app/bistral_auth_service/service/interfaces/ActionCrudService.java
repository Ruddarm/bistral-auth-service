package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.ActionRequestDto;
import com.bistral.app.bistral_auth_service.dtos.ActionResponseDto;
import com.bistral.app.bistral_auth_service.entity.Action;

import java.util.List;
import java.util.UUID;

/**
 *
 */
public interface ActionCrudService {

    /**
     *
     */
    public ActionResponseDto createAction(ActionRequestDto actionRequestDto);

    public Action getActionById(UUID actionId);

    public ActionResponseDto updateAction(ActionRequestDto actionRequestDto);

    public List<ActionResponseDto> getAllActions();




}

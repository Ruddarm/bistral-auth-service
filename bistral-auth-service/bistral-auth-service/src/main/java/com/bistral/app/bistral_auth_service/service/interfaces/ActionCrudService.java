package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.dtos.ActionRequestDto;
import com.bistral.app.bistral_auth_service.dtos.ActionResponseDto;
import com.bistral.app.bistral_auth_service.entity.ActionEntity;

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

    public ActionEntity getActionById(UUID actionId);

    public ActionEntity updateAction(ActionRequestDto actionRequestDto);

    public List<ActionEntity> getAllActions();




}

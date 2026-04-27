package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.dtos.ActionRequestDto;
import com.bistral.app.bistral_auth_service.entity.ActionEntity;
import com.bistral.app.bistral_auth_service.service.interfaces.ActionCrudService;

import java.util.List;
import java.util.UUID;

public class ActionCrudServiceImpl implements ActionCrudService {


    /**
     *
     * @param actionRequestDto
     */
    @Override
    public ActionEntity createAction(ActionRequestDto actionRequestDto) {
        return null;
    }

    @Override
    public ActionEntity getActionById(UUID actionId) {
        return null;
    }

    @Override
    public ActionEntity updateAction(ActionRequestDto actionRequestDto) {
        return null;
    }

    @Override
    public List<ActionEntity> getAllActions() {
        return List.of();
    }
}

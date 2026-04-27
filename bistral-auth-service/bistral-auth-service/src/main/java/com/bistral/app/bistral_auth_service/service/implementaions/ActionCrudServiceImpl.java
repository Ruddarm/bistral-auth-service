package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.ActionRequestDto;
import com.bistral.app.bistral_auth_service.dtos.ActionResponseDto;
import com.bistral.app.bistral_auth_service.entity.ActionEntity;
import com.bistral.app.bistral_auth_service.entity.UserEntity;
import com.bistral.app.bistral_auth_service.exceptions.ResourceNotFoundExceptions;
import com.bistral.app.bistral_auth_service.mapper.ActionMapper;
import com.bistral.app.bistral_auth_service.repository.ActionRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.ActionCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ActionCrudServiceImpl implements ActionCrudService {

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;

    /**
     *
     * @param actionRequestDto
     */
    @Override
    public ActionResponseDto createAction(ActionRequestDto actionRequestDto) {
        UserEntity user = UserEntity.builder()
                .userId(UserContextHolder.getAuthContext()
                        .getUserId()).build();
        return actionMapper.toActionResponse(actionRepository.
                save(ActionEntity.builder()
                        .actionName(actionRequestDto.getActionName().toUpperCase())
                        .createdBy(user)
                        .build()));
    }

    @Override
    public ActionEntity getActionById(UUID actionId) {
        return actionRepository
                .findById(actionId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Action Not found with Id "+actionId,actionId.toString(),"action"));
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

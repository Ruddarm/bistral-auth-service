package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.contexts.UserContextHolder;
import com.bistral.app.bistral_auth_service.dtos.ActionRequestDto;
import com.bistral.app.bistral_auth_service.dtos.ActionResponseDto;
import com.bistral.app.bistral_auth_service.entity.Actions;
import com.bistral.app.bistral_auth_service.entity.Users;
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
        Users user = Users.builder()
                .userId(UserContextHolder.getAuthContext()
                        .getUserId()).build();
        return actionMapper.toActionResponse(actionRepository.
                save(Actions.builder()
                        .actionName(actionRequestDto.getActionName().toUpperCase())
                        .createdBy(user)
                        .build()));
    }

    @Override
    public Actions getActionById(UUID actionId) {
        return actionRepository
                .findById(actionId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Action Not found with Id "+actionId,actionId.toString(),"action"));
    }

    @Override
    public ActionResponseDto updateAction(ActionRequestDto actionRequestDto) {
        return null;
    }

    @Override
    public List<ActionResponseDto> getAllActions() {
        return actionRepository
                .findAll()
                .stream()
                .map(actionMapper::toActionResponse)
                .toList();
    }
}

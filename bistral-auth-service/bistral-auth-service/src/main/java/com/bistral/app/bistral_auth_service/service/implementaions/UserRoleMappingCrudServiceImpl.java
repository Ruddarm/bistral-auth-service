package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingRequestDto;
import com.bistral.app.bistral_auth_service.dtos.UserRoleMappingResponseDto;
import com.bistral.app.bistral_auth_service.entity.RoleEntity;
import com.bistral.app.bistral_auth_service.entity.UserEntity;
import com.bistral.app.bistral_auth_service.entity.UserRoleMappingEntity;
import com.bistral.app.bistral_auth_service.repository.RoleRepository;
import com.bistral.app.bistral_auth_service.repository.UserRepository;
import com.bistral.app.bistral_auth_service.repository.UserRoleMappingRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.RoleUserMappingCrudService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.apache.commons.text.translate.UnicodeUnescaper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor

public class UserRoleMappingCrudServiceImpl implements RoleUserMappingCrudService {

    private final UserRoleMappingRepository userRoleMappingRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Boolean assignRole(UserRoleMappingRequestDto roleMappingRequestDto) {
        UUID userId = roleMappingRequestDto.getUserId();
        UserEntity user = UserEntity.builder()
                                .userId(userId)
                                    .build();
        UUID bistroId = roleMappingRequestDto.getBistroId();
        List<UserRoleMappingEntity> roleMappingEntities  =
                roleMappingRequestDto.getRoleAssignmentDtoList()
                        .stream().map((assignRole)->{
                            return  UserRoleMappingEntity.builder()
                                    .user(user)
                                    .bistro_id(bistroId)
                                    .role(RoleEntity.builder()
                                            .userRoleId(assignRole.getRoleId()).build())
                                    .branch_id(assignRole.getBranchId())
                                    .build();
                            }).toList();
        userRoleMappingRepository.saveAll(roleMappingEntities);
        return Boolean.TRUE;
    }
}

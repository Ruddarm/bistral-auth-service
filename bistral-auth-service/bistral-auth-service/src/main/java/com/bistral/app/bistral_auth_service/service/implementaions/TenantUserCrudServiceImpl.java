package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.dtos.CreateTenantUserDto;
import com.bistral.app.bistral_auth_service.dtos.TenantUserResponseDto;
import com.bistral.app.bistral_auth_service.entity.TenantUser;
import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
import com.bistral.app.bistral_auth_service.mapper.TenantUserMapper;
import com.bistral.app.bistral_auth_service.repository.TenantUserRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantUserCrudService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of {@link  TenantUserCrudService} to provide crud operation for {@link  TenantUser}
 *
 */
@AllArgsConstructor
@Service
public class TenantUserCrudServiceImpl implements TenantUserCrudService {
    
    private final TenantUserRepository tenantUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TenantUserMapper tenantUserMapper;
    
    
    /**
     * Create tenantUser in system.
     *
     * @param tenantUserDto required information for creation of tenantUser
     * @return {@link  TenantUserResponseDto} represent abstraction of {@link TenantUser}
     */
    @Override
    public TenantUserResponseDto createTenantUser(CreateTenantUserDto tenantUserDto) {
        TenantUser tenantUser = tenantUserMapper.fromTenantCreateDto(tenantUserDto);
        tenantUser.setUserHashPasswd(
                bCryptPasswordEncoder.encode(tenantUserDto.getPassword())
        );
        tenantUser = tenantUserRepository.save(tenantUser);
        return tenantUserMapper.toSignupResponse(tenantUser);
    }
    
    /**
     * Find and Return tenantUser of a given id
     *
     * @param userId represent userid
     * @return {@link TenantUser} represent tenantUser belong to given userId.
     */
    @Override
    public TenantUser getTenantUserByUserId(UUID userId) throws UserNotFoundException {
        return tenantUserRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId, "TenantUser not found with given Id " + userId));
    }
    
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

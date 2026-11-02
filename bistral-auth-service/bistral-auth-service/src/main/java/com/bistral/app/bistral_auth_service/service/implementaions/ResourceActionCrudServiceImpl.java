package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.entity.ResourceActionMapping;
import com.bistral.app.bistral_auth_service.repository.ResourceActionsMappingRepository;
import com.bistral.app.bistral_auth_service.service.interfaces.ResourceActionsMappingCrudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class ResourceActionCrudServiceImpl implements ResourceActionsMappingCrudService {
    
    private final ResourceActionsMappingRepository resourceActionsMappingRepository;
    
    
    /**
     * @return
     */
    @Override
    public List<ResourceActionMapping> getAllNonSystemSpecificResourceActionMappings() {
        return resourceActionsMappingRepository.findAllByDeletedAtIsNullAndIsSystemSpecificIsFalse();
    }
}

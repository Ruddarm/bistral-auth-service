package com.bistral.app.bistral_auth_service.service.interfaces;

import com.bistral.app.bistral_auth_service.entity.ResourceActionMapping;

import java.util.List;

public interface ResourceActionsMappingCrudService {
    List<ResourceActionMapping> getAllNonSystemSpecificResourceActionMappings();
}

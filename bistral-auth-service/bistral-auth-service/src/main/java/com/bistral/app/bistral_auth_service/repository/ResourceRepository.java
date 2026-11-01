package com.bistral.app.bistral_auth_service.repository;

import com.bistral.app.bistral_auth_service.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID> {


}

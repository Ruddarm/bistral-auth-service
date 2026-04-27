package com.bistral.app.bistral_auth_service.repository;

import com.bistral.app.bistral_auth_service.entity.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActionRepository extends JpaRepository<ActionEntity, UUID> {

}

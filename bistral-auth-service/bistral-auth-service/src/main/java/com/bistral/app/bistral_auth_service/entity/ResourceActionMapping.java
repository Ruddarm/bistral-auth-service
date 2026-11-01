package com.bistral.app.bistral_auth_service.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Specify the Action Allowed for the given resource
 * If the action is only for system users then isSystemUser must be true.
 * Each Resource And action combination must be unique only.
 *
 */
@Builder
@Entity
@Table
public class ResourceActionMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID resourceActionMappingId;

    @ManyToOne
    @JoinColumn(name = "resource_id",nullable = false,updatable = false)
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "action_id", nullable = false,updatable = false)
    private Action action;


    @Builder.Default
    @Column(nullable = false)
    private boolean isSystemSpecific=true;

    @Column()
    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;




}

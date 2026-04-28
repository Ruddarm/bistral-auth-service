package com.bistral.app.bistral_auth_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 */
@Builder
@Table(
        name = "role_permissions",
        uniqueConstraints =
                {
                        @UniqueConstraint(
                                name = "role_permission",
                                columnNames = {"user_role_id","resource_id","action_id"}
                        ),

                }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RolePermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_permission_id")
    private UUID rolePermissionId;

    @JoinColumn(name = "user_role_id",nullable = false)
    @ManyToOne
    private RoleEntity role;

    @JoinColumn(name = "resource_id",nullable = false)
    @ManyToOne
    private ResourceEntity resource;

    @JoinColumn(name = "action_id",nullable = false)
    @ManyToOne
    private ActionEntity actionEntity;

    @Column(name = "created_at",nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JoinColumn(name="created_by",nullable = false)
    @ManyToOne
    private UserEntity createdBy;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JoinColumn(name="updated_by")
    @ManyToOne
    private UserEntity updatedBy;

}
package com.bistral.app.bistral_auth_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represent Relation between {@link TenantRole} And {@link ResourceActionMapping}
 * Specify the permissions given to a role for the given Tenant.
 * Each Role may access one or more {@link Resource} and can perform one or many {@link  Action}
 */
@Builder
@Table(
        name = "tenant_role_permissions",
        uniqueConstraints =
                {
                        @UniqueConstraint(
                                name = "role_permission",
                                columnNames = {"user_role_id", "resource_id", "action_id"}
                        ),

                }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TenantRolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_permission_id")
    private UUID rolePermissionId;

    @JoinColumn(name = "user_role_id", nullable = false)
    @ManyToOne
    private TenantRole tenantRole;

    @JoinColumn(nullable = false, name = "resource_action_mapping_id")
    @ManyToOne()
    private ResourceActionMapping resourceActionMapping;


    @Column(nullable = false)
    private boolean isAllowed = false;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JoinColumn(name = "created_by", nullable = false)
    @ManyToOne
    private TenantUser createdBy;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JoinColumn(name = "updated_by")
    @ManyToOne
    private TenantUser updatedBy;

}
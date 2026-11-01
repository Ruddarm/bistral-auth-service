package com.bistral.app.bistral_auth_service.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Represent TenantRole of tenantUser in a System. Provide modular and dynamic tenantRole management tu tenantUser define who and what
 * a tenantUser can access.
 * <ul>
 *     <li>For each Bistro one tenantRole should have unique name</li>
 *     <li>Bistro id represent TenantRole Belong to a bistro</li>
 *     <li>By Default Owner TenantRole is Created for each bistro</li>
 * </ul>
 * Each Bistro Have rootRole as root node in the role hierarchy. And each Node must have
 * parentRole if it is not a root node.
 */
@Entity
@Table(name = "tenant_role",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_user_role_name",
                        columnNames = {"lower(role_name)", "bistro_id"}
                )
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tenant_role_id")
    private UUID tenantRoleId;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "bistro_id", nullable = false)
    private UUID bistroId;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;


    @Column(nullable = false)
    private boolean isRootRole = false;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_role_id")
    private TenantRole parentRole;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @JoinColumn(name = "created_by", nullable = false)
    @ManyToOne
    private TenantUser createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private TenantUser updatedBy;


    @OneToMany(mappedBy = "tenantRole")
    private List<TenantRolePermission> tenantRolePermissions;


}


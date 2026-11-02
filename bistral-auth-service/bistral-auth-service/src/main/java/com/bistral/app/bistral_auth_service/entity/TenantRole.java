package com.bistral.app.bistral_auth_service.entity;


import com.bistral.app.bistral_auth_service.utility.ValidationUtil;
import jakarta.persistence.*;
import jakarta.validation.Validation;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
@Getter
@Setter
@Builder
@Slf4j
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
    
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
    
    
    @Builder.Default
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "tenantRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TenantRolePermission> tenantRolePermissions = new ArrayList<>();
    
    public void addTenantRolePermission(TenantRolePermission tenantRolePermission) {
        ValidationUtil.notNull(tenantRolePermission, "tenantRolePermission");
        if (tenantRolePermission.getTenantRole() != null) {
            log.debug("tenantRolePermission is assigned to another Role : {}", tenantRolePermission.getTenantRole());
        }
        this.tenantRolePermissions.add(tenantRolePermission);
        if (!tenantRolePermissions.contains(tenantRolePermission)) {
            tenantRolePermissions.add(tenantRolePermission);
            tenantRolePermission.setTenantRole(this);
        }
    }
    
    public void removeTenantRolePermission(TenantRolePermission tenantRolePermission) {
        if (tenantRolePermissions.contains(tenantRolePermission)) {
            tenantRolePermissions.remove(tenantRolePermission);
            tenantRolePermission.setTenantRole(null);
        }
    }
    
    
}


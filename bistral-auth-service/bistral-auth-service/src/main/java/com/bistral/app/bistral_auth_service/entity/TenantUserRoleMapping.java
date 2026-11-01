package com.bistral.app.bistral_auth_service.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represent A mapping between {@link TenantUser} tenantUser  and {@link TenantRole} tenantRole,
 * mean a tenantRole of <code>Bistro</code> assigned to tenantUser
 * <note>
 *     Each tenantUser must have assign one tenantRole only once , but tenantUser can have
 *     multiple roles in one or different bistros.
 * </note>
 * <p>
 * <Code>Fields Information</code>
 * <p>
 *     <code>UserId</code>
 *     <ul>
 *         <li>Represent tenantUser whom tenantRole is assigned</li>
 *     </ul>
 * </p>
 * </p>
 */
@Entity
@Table
        (name = "user_role_mappings",
                uniqueConstraints =
                        {
                                @UniqueConstraint(
                                        name = "unique_user_role",
                                        columnNames = {
                                                "user_id","role_id","bistro_id","branch_id"
                                        }
                                )
                        }
        )
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TenantUserRoleMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_role_mapping_id")
    private UUID userRoleMappingId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private TenantRole tenantRole;

    @ManyToOne()
    @JoinColumn(name = "user_id" , nullable = false)
    private TenantUser tenantUser;

    @Column(name = "bistro_id", nullable = false)
    private UUID bistroId;

    @Column(name = "branch_id", nullable = false)
    private  UUID branchId;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private TenantUser createdBy;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by", nullable = false)
    private TenantUser updated_by;

}

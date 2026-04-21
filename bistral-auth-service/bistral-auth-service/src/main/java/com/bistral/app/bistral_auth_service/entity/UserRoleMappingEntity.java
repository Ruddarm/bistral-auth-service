package com.bistral.app.bistral_auth_service.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represent A mapping between {@link UserEntity} user  and {@link RoleEntity} role,
 * mean a role of <code>Bistro</code> assigned to user
 * <note>
 *     Each user must have assign one role only once , but user can have
 *     multiple roles in one or different bistros.
 * </note>
 * <p>
 * <Code>Fields Information</code>
 * <p>
 *     <code>UserId</code>
 *     <ul>
 *         <li>Represent user whom role is assigned</li>
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
public class UserRoleMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_role_mapping_id")
    private UUID userRoleMappingId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @ManyToOne()
    @JoinColumn(name = "user_id" , nullable = false)
    private UserEntity user;

    @Column(name = "bistro_id", nullable = false)
    private UUID bistro_id;

    @Column(name = "branch_id", nullable = false)
    private  UUID branch_id;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by", nullable = false)
    private UserEntity updated_by;

}

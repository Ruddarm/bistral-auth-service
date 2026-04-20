package com.bistral.app.bistral_auth_service.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represent Role of user in a System. Provide modular and dynamic role management tu user define who and what
 * a user can access.
 * <ul>
 *     <li>For each Bistro one role should have unique name</li>
 *     <li>Bistro id represent Role Belong to a bistro</li>
 *     <li>By Default Owner Role is Created for each bistro</li>
 * </ul>
 */
@Entity
@Table(name = "user_roles",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_user_role_name",
                columnNames = {"lower(role_name)","bistro_id"}
        )
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_role_id")
    private UUID userRoleId;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "bistro_id",nullable = false)
    private UUID bistroId;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @JoinColumn(name = "created_by",nullable = false)
    @ManyToOne
    private UserEntity createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private UserEntity updatedBy;

    @Override
    public String toString() {
        return "role Name %s , role_id %s , createdAt %s , createdBy %s "
                .formatted(this.roleName
                    ,this.getUserRoleId()==null?null:this.getUserRoleId().toString(),
                        this.createdAt==null?null:this.createdAt,
                        this.createdBy==null?null:this.createdBy
                );
    }
}


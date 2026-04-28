package com.bistral.app.bistral_auth_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represent Action that can be performed  by {@link UserEntity} on {@link ResourceEntity}
 * A Action Need to be unique through applications.
 */
@Entity
@Table(name = "actions",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_action_name",
                columnNames = {"action_name"}
        )
        }
)
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class ActionEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "action_id")
    private UUID actionId;

    @Column(name = "action_name", nullable = false)
    private String actionName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by")
    private UserEntity updated_by;

}

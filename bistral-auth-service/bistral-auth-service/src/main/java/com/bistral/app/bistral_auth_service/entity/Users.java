package com.bistral.app.bistral_auth_service.entity;

import com.bistral.app.bistral_auth_service.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Represent user in a system and user can be of two type
 * <ul>
 *     <li><code>System User</code> Represent user have access to resource related to system </li>
 *     <li><code>Tenant User</code> Represent user a real user of system who will use system for business purpose and
 *     own resource like bistro, branches order etc
 *     </li>
 *     <li>Each <code>User</code> must have <b>unique email and username</b></li>
 * </ul>
 * <p>Not Null Fields
 *  <ul>
 *      <li>userName</li>
 *      <li>userEmail</li>
 *      <li>userHashPassword</li>
 *  </ul>
 *  <p>
 *      <code>Note</code>
 *      <ul>
 *          <li>A username and userEmail should be unique where deleted at is null</li>
 *          <li>To ensure all active user have unique email and username</li>
 *      </ul>
 *  </p>
 * </p>
 *
 *
 */
@Entity
@Table(name = "bistral_users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private  UUID userId;

    @Column(name = "user_name", nullable = false)
    private  String username;


    @Column(name = "user_email", nullable = false)
    private  String userEmail;

    @Column(name = "password_hash", nullable = false)
    private  String userHashPasswd;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userTypeEnum;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder.Default
    @Column(name = "is_active", nullable = false, columnDefinition = "DEFAULT 1")
    private  Boolean isActive = true;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.userHashPasswd;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

}

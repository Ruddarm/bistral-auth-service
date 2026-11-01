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
 * Represent tenantUser in a system and tenantUser can be of two type
 * <ul>
 *     <li><code>Tenant TenantUser</code> Represent tenantUser a real tenantUser of system who will use system for business purpose and
 *     own resource like bistro, branches order etc
 *     </li>
 *     <li>Each <code>TenantUser</code> must have <b>unique email and username</b></li>
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
 *          <li>To ensure all active tenantUser have unique email and username</li>
 *      </ul>
 *  </p>
 * </p>
 *
 *
 */
@Entity
@Table(name = "tenant_user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantUser implements UserDetails {

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

    @Column(nullable = false)
    private UUID tenantId;


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

package com.bistral.app.bistral_auth_service.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

/**
 * Api Contract object represent sign up field for <code>userEntity</code>
 * <p>
 *     <ul>
 *         <li><code>userName</code> must not be null or empty</li>
 *         <li><code>userEmail</code>tenantUser email should be valid and unique</li>
 *         <li><code>password</code> A password should consist of alphabets, number and special symbols with minimum length of 8 </li>
 *     </ul>
 * </p>
 *
 */
@Data
@Builder
public class CreateTenantUserDto {

    @NotNull
    @Length(
            min = 4,
            max = 100,
            message = "userName should have length minimum 4 and maximum 100"
    )
    private String userName;
    @Email
    @Length(
            min = 4,
            max = 100,
            message = "userEmail should have length minimum 4 and maximum 100"
    )
    private String userEmail;
    
    @NotNull
    private UUID tenantId;

}

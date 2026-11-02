package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.dtos.*;
import com.bistral.app.bistral_auth_service.service.interfaces.AuthService;
import com.bistral.app.bistral_auth_service.service.interfaces.TenantUserCrudService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("")
public class TenantAuthController {

    private final TenantUserCrudService tenantUserCrudService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody UserLoginRequest userLoginRequest, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        AuthResponse response = authService.authenticate(userLoginRequest);
        String cookie = String.format(
                "user_refresh_token=%s; Path=/; HttpOnly; SameSite=Lax; Max-Age=%d",
                response.getRefreshToken(), 7 * 24 * 60 * 60);
        servletResponse.setHeader("Set-Cookie", cookie);
        return
                ResponseEntity.ok(ApiResponse.<AuthResponse>builder()
                        .data(response)
                        .isError(false)
                        .message("Log in Successfully")
                        .build()
                );
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<TenantUserResponseDto>> signUp(@Valid @RequestBody CreateTenantUserDto userSignUpDto) {
        TenantUserResponseDto tenantUserResponseDto = userCrudService.createUser(userSignUpDto);
        return ResponseEntity.created(URI.create("/users/" + tenantUserResponseDto.getUserId()))
                .body(ApiResponse.<TenantUserResponseDto>builder()
                .message("Sign up Success fully")
                .data(tenantUserResponseDto)
                .build());
    }


    @PostMapping("/switch/context")
    public ApiResponse<AuthResponse> switchLoginContext(@Valid @RequestBody LoginContext loginContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        AuthResponse response = authService.switchLoginContext(loginContext);
        String cookie = String.format(
                "user_refresh_token=%s; Path=/; HttpOnly; SameSite=Lax; Max-Age=%d",
                response.getRefreshToken(), 7 * 24 * 60 * 60);
        servletResponse.setHeader("Set-Cookie", cookie);
        return
                ApiResponse.<AuthResponse>builder()
                        .data(response)
                        .isError(false)
                        .message("Log in Successfully")
                        .build();
    }


    @PostMapping("/refresh-token")
    public ApiResponse<AuthResponse> refreshToken(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        String token = request.getHeader("Authorization");
        if(token==null || token.isEmpty() || token.startsWith("Bearer "))
            throw new IllegalArgumentException("Invalid Token");

        AuthResponse response = authService
                .refreshToken(token.split("Bearer ")[1]);
        String cookie = String.format(
                "user_refresh_token=%s; Path=/; HttpOnly; SameSite=Lax; Max-Age=%d",
                response.getRefreshToken(), 7 * 24 * 60 * 60);
        httpServletResponse.setHeader("Set-Cookie", cookie);
        return
                ApiResponse.<AuthResponse>builder()
                        .data(response)
                        .isError(false)
                        .message("Log in Successfully")
                        .build();

    }

}

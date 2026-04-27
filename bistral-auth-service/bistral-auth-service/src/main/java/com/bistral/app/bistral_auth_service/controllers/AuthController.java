package com.bistral.app.bistral_auth_service.controllers;


import com.bistral.app.bistral_auth_service.dtos.*;
import com.bistral.app.bistral_auth_service.service.interfaces.AuthService;
import com.bistral.app.bistral_auth_service.service.interfaces.UserCrudService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("")
public class AuthController {

    private final UserCrudService userCrudService;
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
    public ResponseEntity<ApiResponse<UserResponseDto>> signUp(@Valid @RequestBody CreateUserDto userSignUpDto) {
        UserResponseDto userResponseDto = userCrudService.createUser(userSignUpDto);
        return ResponseEntity.created(URI.create("/users/" + userResponseDto.getUserId()))
                .body(ApiResponse.<UserResponseDto>builder()
                .message("Sign up Success fully")
                .data(userResponseDto)
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

}

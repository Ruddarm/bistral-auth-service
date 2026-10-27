package com.bistral.app.bistral_auth_service;

import com.bistral.app.bistral_auth_service.dtos.UserLoginRequest;
import com.bistral.app.bistral_auth_service.service.interfaces.AuthService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BistralAuthServiceApplicationTests {

    @Autowired
	private  AuthService authService;

    @Test
	void contextLoads() {
	}

    @Test
    public void testing() throws Exception {
        UserLoginRequest userLoginRequest =
                UserLoginRequest.builder()
                        .userName("firstUser@gmail.com")
                        .password("pswd123").build();
        authService.authenticate(userLoginRequest);
    }

}

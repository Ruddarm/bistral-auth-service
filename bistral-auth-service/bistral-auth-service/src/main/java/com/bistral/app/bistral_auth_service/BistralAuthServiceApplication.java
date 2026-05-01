package com.bistral.app.bistral_auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class BistralAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BistralAuthServiceApplication.class, args);
	}

}

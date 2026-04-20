package com.bistral.app.bistral_auth_service.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/role")
public class UserRoleMappingController {

    @PostMapping("/assign")
    public void assignRole(){

    }
}

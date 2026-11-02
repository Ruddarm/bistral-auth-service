package com.bistral.app.bistral_auth_service.config;


import com.bistral.app.bistral_auth_service.service.interfaces.TenantUserCrudService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider(TenantUserCrudService userCrudService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userCrudService);
        daoAuthenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
        return  daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(TenantUserCrudService userCrudService){
        return  new ProviderManager(List.of(getAuthenticationProvider(userCrudService)));
    }

    

}

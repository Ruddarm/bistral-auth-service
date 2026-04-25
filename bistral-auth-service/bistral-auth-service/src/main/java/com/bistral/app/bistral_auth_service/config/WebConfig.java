package com.bistral.app.bistral_auth_service.config;

//import com.bistral.app.bistral_auth_service.filters.JwtTokenAuthFilter;
import com.bistral.app.bistral_auth_service.interceptors.UserLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebConfig  implements WebMvcConfigurer {

    private final UserLoginInterceptor userLoginInterceptor;

    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) ->
                        auth
                                .requestMatchers("/**").permitAll().anyRequest()
                                .authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginInterceptor);
    }
}

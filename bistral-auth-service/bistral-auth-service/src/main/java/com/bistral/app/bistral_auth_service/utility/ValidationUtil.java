package com.bistral.app.bistral_auth_service.utility;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationUtil {
    
    
    public static void notNull(Object object, String message) {
        if (object == null) {
            log.error("Null Validation Failed  {}", message);
            throw new IllegalArgumentException(message);
        }
    }
}

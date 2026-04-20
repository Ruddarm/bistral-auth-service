package com.bistral.app.bistral_auth_service.exceptions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundExceptions extends RuntimeException {
    private  String resourceName;
    private  String resourceId;

    public ResourceNotFoundExceptions(String message,String resourceId,String resourceName) {
        super(message);
        this.resourceId=resourceId;
        this.resourceName=resourceName;
    }
}

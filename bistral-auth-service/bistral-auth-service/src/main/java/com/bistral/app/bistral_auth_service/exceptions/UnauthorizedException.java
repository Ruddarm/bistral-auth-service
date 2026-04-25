package com.bistral.app.bistral_auth_service.exceptions;

public class UnauthorizedException extends RuntimeException {


    public UnauthorizedException(String message) {
        super(message);
    }
}

package com.bistral.app.bistral_auth_service.exceptions;

public class InvalidUserName extends RuntimeException {
  public InvalidUserName(String message) {
    super(message);
  }
}

package com.bistral.app.bistral_auth_service.exceptions;


import com.bistral.app.bistral_auth_service.dtos.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse<Map<String, String>>> handelValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach((error) -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(
                ApiResponse
                        .<Map<String, String>>
                                builder()
                        .message("Argument Failed")
                        .data(errors)
                        .isError(true)
                        .build()
        );

    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiResponse<Void>> handleBadRequestExceptions(IllegalArgumentException exception) {
        return ResponseEntity.
                badRequest().body(
                        ApiResponse.<Void>builder()
                                .isError(true)
                                .message(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleOtherExceptions(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity.
                internalServerError().body(
                        ApiResponse.<Void>builder()
                                .isError(true)
                                .message(exception.getMessage())
                                .build()
                );
    }
}

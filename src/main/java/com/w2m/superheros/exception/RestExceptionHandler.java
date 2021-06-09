package com.w2m.superheros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ApiError> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(ApiError.builder().
                        message(ex.getMessage()).
                        timestamp(Instant.now()).
                        status(HttpStatus.NOT_FOUND)
                        .build());
    }
}

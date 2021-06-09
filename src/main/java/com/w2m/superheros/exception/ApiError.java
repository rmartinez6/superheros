package com.w2m.superheros.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiError {
    private HttpStatus status;
    private Instant timestamp = Instant.now();
    private String message;


}
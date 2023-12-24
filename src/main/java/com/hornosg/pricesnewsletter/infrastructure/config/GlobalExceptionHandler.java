package com.hornosg.pricesnewsletter.infrastructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        Map<String, Object> ErrorMessage = new HashMap<>();
        ErrorMessage.put("timestamp", LocalDateTime.now());
        ErrorMessage.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        ErrorMessage.put("error", "Unexpected Error");
        ErrorMessage.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessage);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(Exception e) {
        Map<String, Object> ErrorMessage = new HashMap<>();
        ErrorMessage.put("timestamp", LocalDateTime.now());
        ErrorMessage.put("statusCode", HttpStatus.BAD_REQUEST.value());
        ErrorMessage.put("error", "BadRequest Error");
        ErrorMessage.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage);
    }
}

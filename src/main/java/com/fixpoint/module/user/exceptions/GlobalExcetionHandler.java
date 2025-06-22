package com.fixpoint.module.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExcetionHandler {
    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<Object> globlaExceptionHandler(ResouceNotFoundException ex){
        String message = ex.getMessage();
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("success", true);
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

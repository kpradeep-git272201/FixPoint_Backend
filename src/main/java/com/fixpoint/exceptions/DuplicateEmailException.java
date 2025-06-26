package com.fixpoint.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(String.valueOf(new ResponseEntity<>(message, HttpStatus.CONFLICT)));
    }
}

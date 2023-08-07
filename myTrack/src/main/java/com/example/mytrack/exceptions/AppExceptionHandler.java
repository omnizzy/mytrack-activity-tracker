package com.example.mytrack.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<?> emailException(InvalidDataException invalidDataException) {
        return ResponseEntity.badRequest().body(invalidDataException.getLocalizedMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> findTaskException(NotFoundException notFoundException) {
        return ResponseEntity.badRequest().body(notFoundException.getLocalizedMessage());
    }

}

package com.dev.apirest.controllers.exceptions;

import com.dev.apirest.services.exceptions.DatabaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.dev.apirest.services.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
            String error = "Dados não encontrados";
            HttpStatus status = HttpStatus.NOT_FOUND;
            StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
            return ResponseEntity.status(status).body(err);
        }
        
    /*
    @ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleIntegrityConstraintViolationException(JdbcSQLIntegrityConstraintViolationException ex) {
            String error = "Database error";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}


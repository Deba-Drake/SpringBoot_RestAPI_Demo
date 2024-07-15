package com.SocialMediaApp.WebServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse
                (
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "Not Found",
                        ex.getMessage(),
                        request.getDescription(false).substring(4)
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernamenullException.class)
    public ResponseEntity<CustomErrorResponse> handleUserNotFoundException(UsernamenullException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse
                (
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Bad Request",
                        ex.getMessage(),
                        request.getDescription(false).substring(4)
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
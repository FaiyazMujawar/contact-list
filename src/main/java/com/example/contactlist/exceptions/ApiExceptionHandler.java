package com.example.contactlist.exceptions;

import com.example.contactlist.api.response.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException exception) {
        var response = ApiErrorResponse
                .builder()
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();
        return ResponseEntity
                .status(exception.getStatus())
                .body(response);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        var response = ApiErrorResponse
                .builder()
                .message(exception.getMessage())
                .build();
        return ResponseEntity
                .internalServerError()
                .body(response);
    }
}
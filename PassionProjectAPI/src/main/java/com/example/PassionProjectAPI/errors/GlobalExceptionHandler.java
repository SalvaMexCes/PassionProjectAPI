package com.example.PassionProjectAPI.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExchangeRateNotFoundException.class)
    public ResponseEntity<Object> handleExchangeRateNotFoundException(
            ExchangeRateNotFoundException ex, WebRequest request) {

        String errorMessage = ex.getMessage();
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}

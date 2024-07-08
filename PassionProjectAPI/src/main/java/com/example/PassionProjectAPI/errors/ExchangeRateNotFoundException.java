package com.example.PassionProjectAPI.errors;

public class ExchangeRateNotFoundException extends RuntimeException {

    public ExchangeRateNotFoundException(String message) {
        super(message);
    }
}

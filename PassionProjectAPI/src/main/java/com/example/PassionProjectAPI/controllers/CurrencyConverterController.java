package com.example.PassionProjectAPI.controllers;

import com.example.PassionProjectAPI.models.CurrencyConversionRequest;
import com.example.PassionProjectAPI.models.CurrencyConversionResponse;
import com.example.PassionProjectAPI.models.CurrencyExchangeRate;
import com.example.PassionProjectAPI.services.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CurrencyConverterController {

    @Autowired
    private CurrencyConverterService converterService;

    @GetMapping("/convert")
    public ResponseEntity<CurrencyConversionResponse> convertCurrency(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam double amount) {

        CurrencyConversionRequest request = new CurrencyConversionRequest();
        request.setFromCurrency(fromCurrency);
        request.setToCurrency(toCurrency);
        request.setAmount(amount);

        CurrencyConversionResponse response = converterService.convertCurrency(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/convert")
    public ResponseEntity<CurrencyConversionResponse> convertCurrency(@RequestBody CurrencyConversionRequest request) {
        CurrencyConversionResponse response = converterService.convertCurrency(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rates")
    public ResponseEntity<List<CurrencyExchangeRate>> getAllExchangeRates() {
        List<CurrencyExchangeRate> rates = converterService.getAllExchangeRates();
        return ResponseEntity.ok(rates);
    }

}

package com.example.PassionProjectAPI.services;


import com.example.PassionProjectAPI.errors.ExchangeRateNotFoundException;
import com.example.PassionProjectAPI.models.CurrencyConversionRequest;
import com.example.PassionProjectAPI.models.CurrencyConversionResponse;
import com.example.PassionProjectAPI.models.CurrencyExchangeRate;
import com.example.PassionProjectAPI.repos.CurrencyExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyConverterService {

    @Autowired
    private CurrencyExchangeRateRepository currencyExchangeRateRepository;

    public CurrencyConversionResponse convertCurrency(CurrencyConversionRequest request) {

        //Fetches exchange rate for the requested currency pair
        Optional<CurrencyExchangeRate> optionalRate = Optional.ofNullable(currencyExchangeRateRepository
                .findByFromCurrencyAndToCurrency(request.getFromCurrency(), request.getToCurrency()));

        if (!optionalRate.isPresent()) {
            throw new ExchangeRateNotFoundException("Exchange rate not found for currency pair: " +
                    request.getFromCurrency() + " to " + request.getToCurrency());
        }

        CurrencyExchangeRate exchangeRate = optionalRate.get();
        double convertedAmount = request.getAmount() * exchangeRate.getExchangeRate();

        CurrencyConversionResponse response = new CurrencyConversionResponse();
        response.setFromCurrency(request.getFromCurrency());
        response.setToCurrency(request.getToCurrency());
        response.setConvertedAmount(convertedAmount);

        return response;
    }


    public List<CurrencyExchangeRate> getAllExchangeRates() {
        List<CurrencyExchangeRate> rates = currencyExchangeRateRepository.findAll();


        if (rates.isEmpty()) {
            rates.add(new CurrencyExchangeRate("USD", "EUR", 0.85));
            rates.add(new CurrencyExchangeRate("USD", "GBP", 0.75));
            rates.add(new CurrencyExchangeRate("EUR", "USD", 1.18));
            rates.add(new CurrencyExchangeRate("GBP", "USD", 1.33));
        }

        List<CurrencyExchangeRate> updatedRates = new ArrayList<>();
        for (CurrencyExchangeRate rate : rates) {
            if ("USD".equals(rate.getFromCurrency()) && "EUR".equals(rate.getToCurrency())) {
                rate.setExchangeRate(rate.getExchangeRate() * 1.1);
            } else if ("USD".equals(rate.getFromCurrency()) && "GBP".equals(rate.getToCurrency())) {
                rate.setExchangeRate(rate.getExchangeRate() * 0.9);
            }
            // Add more cases if needed
            updatedRates.add(rate);
        }

        return updatedRates;
    }
}

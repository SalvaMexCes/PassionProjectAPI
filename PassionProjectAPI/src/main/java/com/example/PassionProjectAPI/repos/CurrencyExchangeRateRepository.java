package com.example.PassionProjectAPI.repos;

import com.example.PassionProjectAPI.models.CurrencyExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRateRepository extends JpaRepository<CurrencyExchangeRate, Long> {
    CurrencyExchangeRate findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}

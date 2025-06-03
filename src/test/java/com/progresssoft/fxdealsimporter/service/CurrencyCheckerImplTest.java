package com.progresssoft.fxdealsimporter.service;

import static org.junit.jupiter.api.Assertions.*;


import com.progresssoft.fxdealsimporter.handelException.CurrencyNotFoundException;
import com.progresssoft.fxdealsimporter.handelException.InvalidCurrencyException;
import com.progresssoft.fxdealsimporter.config.CurrencyProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class CurrencyCheckerImplTest {

    private CurrencyCheckerImpl currencyChecker;

    @BeforeEach
    void setUp() {
        CurrencyProperties mockCurrencyProperties = new CurrencyProperties();
        mockCurrencyProperties.setCurrencies(List.of("USD", "EUR", "GBP"));
        currencyChecker = new CurrencyCheckerImpl(mockCurrencyProperties);
    }

    @Test
    void givenValidCurrencies_whenCheckCurrencyExchange_thenNoExceptionThrown() {
        assertDoesNotThrow(() -> currencyChecker.checkCurrencyExchange("USD", "EUR"));
    }

    @Test
    void givenInvalidFormatFromCurrency_whenCheckCurrencyExchange_thenThrowInvalidCurrencyException() {
        InvalidCurrencyException exception = assertThrows(InvalidCurrencyException.class, () ->
                currencyChecker.checkCurrencyExchange("US", "EUR"));
        assertEquals("Invalid from currency format", exception.getMessage());
    }

    @Test
    void givenInvalidFormatToCurrency_whenCheckCurrencyExchange_thenThrowInvalidCurrencyException() {
        InvalidCurrencyException exception = assertThrows(InvalidCurrencyException.class, () ->
                currencyChecker.checkCurrencyExchange("USD", "EU"));
        assertEquals("Invalid to currency  format", exception.getMessage());
    }

    @Test
    void givenSameCurrencies_whenCheckCurrencyExchange_thenThrowInvalidCurrencyException() {
        InvalidCurrencyException exception = assertThrows(InvalidCurrencyException.class, () ->
                currencyChecker.checkCurrencyExchange("USD", "USD"));
        assertTrue(exception.getMessage().contains("cannot be the same"));
    }

    @Test
    void givenUnsupportedFromCurrency_whenCheckCurrencyExchange_thenThrowCurrencyNotFoundException() {
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () ->
                currencyChecker.checkCurrencyExchange("JPY", "EUR"));
        assertEquals("Unsupported currency: JPY", exception.getMessage());
    }

    @Test
    void givenUnsupportedToCurrency_whenCheckCurrencyExchange_thenThrowCurrencyNotFoundException() {
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () ->
                currencyChecker.checkCurrencyExchange("USD", "INR"));
        assertEquals("Unsupported currency: INR", exception.getMessage());
    }
}

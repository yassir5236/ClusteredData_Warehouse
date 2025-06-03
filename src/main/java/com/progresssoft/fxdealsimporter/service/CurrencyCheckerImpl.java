    package com.progresssoft.fxdealsimporter.service;

    import com.progresssoft.fxdealsimporter.HandelException.CurrencyNotFoundException;
    import com.progresssoft.fxdealsimporter.HandelException.InvalidCurrencyException;
    import com.progresssoft.fxdealsimporter.config.CurrencyProperties;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Component;

    import java.util.Set;
    @Component
    @RequiredArgsConstructor
    public class CurrencyCheckerImpl implements CurrencyChecker {
        private final String CURRENCYPATTERN = "^[A-Z]{3}$";
        private final CurrencyProperties currencyProperties;


        @Override
        public void checkCurrencyExchange ( String fromCurrency, String toCurrency ) {
            checkPattern(fromCurrency, toCurrency);
            validateCurrencyDifferentiation(fromCurrency, toCurrency);
            checkCurrencyExistence(toCurrency);
            checkCurrencyExistence(fromCurrency);
        }

        private boolean isMatches ( String fromCurrency ) {
            return fromCurrency.matches(CURRENCYPATTERN);
        }

        private void checkPattern ( String fromCurrency, String toCurrency ) {

            if (!isMatches(fromCurrency)) {
                throw new InvalidCurrencyException("Invalid from currency format");
            }
            if (!isMatches(toCurrency)) {
                throw new InvalidCurrencyException("Invalid to currency  format");
            }
        }


        private void validateCurrencyDifferentiation ( String fromCurrency, String toCurrency ) {
            if (fromCurrency.equals(toCurrency)) {
                String errorMessage = String.format("fromCurrency [%s] and toCurrency [%s] cannot be the same.",
                        fromCurrency, toCurrency);
                throw new InvalidCurrencyException(errorMessage);
            }
        }



        private void checkCurrencyExistence(String value) {
            Set<String> validCurrencies = currencyProperties.getCurrencySet();
            if (!validCurrencies.contains(value.toUpperCase())) {
                throw new CurrencyNotFoundException("Unsupported currency: " + value);
            }
        }



    }



    package com.progresssoft.fxdealsimporter.service;

    import com.progresssoft.fxdealsimporter.handelException.CurrencyNotFoundException;
    import com.progresssoft.fxdealsimporter.handelException.InvalidCurrencyException;
    import com.progresssoft.fxdealsimporter.config.CurrencyProperties;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;

    import java.util.Set;
    @Service
    @RequiredArgsConstructor
    @Slf4j
    public class CurrencyCheckerImpl implements CurrencyChecker {
        private final String CURRENCYPATTERN = "^[A-Z]{3}$";
        private final CurrencyProperties currencyProperties;


        @Override
        public void checkCurrencyExchange ( String fromCurrency, String toCurrency ) {
            log.info("Starting currency exchange validation: fromCurrency={}, toCurrency={}", fromCurrency, toCurrency);
            checkPattern(fromCurrency, toCurrency);
            validateCurrencyDifferentiation(fromCurrency, toCurrency);
            checkCurrencyExistence(toCurrency);
            checkCurrencyExistence(fromCurrency);
            log.info("Currency exchange validation passed for: fromCurrency={}, toCurrency={}", fromCurrency, toCurrency);

        }

        private boolean isMatches ( String fromCurrency ) {
            return fromCurrency.matches(CURRENCYPATTERN);
        }

        private void checkPattern ( String fromCurrency, String toCurrency ) {



            if (!isMatches(fromCurrency)) {
                log.error("Invalid currency format for fromCurrency: '{}'", fromCurrency);
                throw new InvalidCurrencyException("Invalid from currency format");
            }
            if (!isMatches(toCurrency)) {
                log.error("Invalid currency format for toCurrency: '{}'", toCurrency);
                throw new InvalidCurrencyException("Invalid to currency  format");
            }
        }


        private void validateCurrencyDifferentiation ( String fromCurrency, String toCurrency ) {
            if (fromCurrency.equals(toCurrency)) {
                String errorMessage = String.format("fromCurrency [%s] and toCurrency [%s] cannot be the same.",
                        fromCurrency, toCurrency);
                log.error("Currency differentiation validation failed: {}", errorMessage);
                throw new InvalidCurrencyException(errorMessage);
            }
        }



        private void checkCurrencyExistence(String value) {
            Set<String> validCurrencies = currencyProperties.getCurrencySet();
            log.debug("Checking existence of currency: '{}'", value);
            if (!validCurrencies.contains(value.toUpperCase())) {
                log.error("Currency not supported: '{}'", value);
                throw new CurrencyNotFoundException("Unsupported currency: " + value);
            }
        }



    }



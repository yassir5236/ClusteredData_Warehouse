package com.progresssoft.fxdealsimporter.handelException;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException ( String message ) {
        super(message);
    }
}

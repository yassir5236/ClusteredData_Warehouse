package com.progresssoft.fxdealsimporter.HandelException;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException ( String message ) {
        super(message);
    }
}

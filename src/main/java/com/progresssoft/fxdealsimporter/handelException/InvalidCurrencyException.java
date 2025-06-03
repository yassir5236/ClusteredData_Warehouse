package com.progresssoft.fxdealsimporter.handelException;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException ( String message ) {
        super(message);
    }
}

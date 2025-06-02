package com.progresssoft.fxdealsimporter.HandelException;

public class RequestAlreadyExistException extends RuntimeException{
    public RequestAlreadyExistException ( String message) {
        super(message);
    }
}

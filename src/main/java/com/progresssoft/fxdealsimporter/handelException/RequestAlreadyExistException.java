package com.progresssoft.fxdealsimporter.handelException;

public class RequestAlreadyExistException extends RuntimeException{
    public RequestAlreadyExistException ( String message) {
        super(message);
    }
}

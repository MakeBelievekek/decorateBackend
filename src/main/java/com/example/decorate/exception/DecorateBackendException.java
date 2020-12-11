package com.example.decorate.exception;

public class DecorateBackendException extends RuntimeException {
    public DecorateBackendException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public DecorateBackendException(String exMessage) {
        super(exMessage);
    }
}

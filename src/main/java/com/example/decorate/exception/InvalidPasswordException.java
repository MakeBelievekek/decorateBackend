package com.progmasters.reactblog.exception;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String invalidPassword) {
        super(invalidPassword);
    }
}

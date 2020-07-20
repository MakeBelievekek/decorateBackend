package com.example.decorate.exception;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String invalidPassword) {
        super(invalidPassword);
    }
}

package com.example.repaso.exception;

// Archivo: exception/EmailAlreadyExistsException.java
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
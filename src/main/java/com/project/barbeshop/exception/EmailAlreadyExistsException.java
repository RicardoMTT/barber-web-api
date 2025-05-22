package com.project.barbeshop.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super("El email ya está registrado: " + email);
    }
}

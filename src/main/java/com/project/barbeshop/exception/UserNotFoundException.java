package com.project.barbeshop.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email) {
        super("Usuario no encontrado con email: " + email);
    }
}

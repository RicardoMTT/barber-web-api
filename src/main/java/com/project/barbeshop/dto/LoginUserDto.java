package com.project.barbeshop.dto;


import jakarta.validation.constraints.NotBlank;

public class LoginUserDto {

    @NotBlank(message = "Email is requerido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
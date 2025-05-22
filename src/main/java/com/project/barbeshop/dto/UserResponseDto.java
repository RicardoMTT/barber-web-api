package com.project.barbeshop.dto;

import java.util.List;

public class UserResponseDto {

    private String fullName;
    private String email;
    private List<String> roles;

    // Constructores
    public UserResponseDto() {
    }

    public UserResponseDto(String fullName, String email, List<String> roles) {
        this.fullName = fullName;
        this.email = email;
        this.roles = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

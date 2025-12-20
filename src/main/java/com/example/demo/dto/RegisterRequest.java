package com.example.demo.dto;

public class RegisterRequest {
    private String email;
    private String password;
    private String role;

    // Constructor
    public RegisterRequest() {}
    public RegisterRequest(String email, String password, String role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}

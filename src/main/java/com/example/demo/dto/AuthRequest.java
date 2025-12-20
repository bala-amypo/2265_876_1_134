package com.example.demo.dto;

public class AuthRequest {
    private String email;
    private String password;

    // Constructor
    public AuthRequest() {}
    public AuthRequest(String email, String password){
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}

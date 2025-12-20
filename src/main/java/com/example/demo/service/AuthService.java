package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;

public interface AuthService {
    AppUser register(RegisterRequest request);
    String login(AuthRequest request);

    // Add this
    AppUser getUserByEmail(String email);
}

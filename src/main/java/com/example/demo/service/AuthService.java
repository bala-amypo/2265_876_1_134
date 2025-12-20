package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;

public interface AuthService {
    AppUser register(RegisterRequest request);  // match controller
    String login(AuthRequest request);          // match controller
}

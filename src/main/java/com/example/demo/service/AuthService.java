package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.model.AppUser;

public interface AuthService {

    AppUser login(AuthRequest request);

    AppUser getUserByEmail(String email);
}

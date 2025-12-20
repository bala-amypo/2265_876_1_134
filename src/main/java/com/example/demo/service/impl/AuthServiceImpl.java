package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    private final JwtTokenProvider jwtTokenProvider;
    private final AppUserRepository userRepository;

    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider, AppUserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    // Register a new user
    public AppUser registerUser(RegisterRequest request) {
        AppUser user = new AppUser();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // you should hash the password in production
        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    // Authenticate user and generate JWT
    public String authenticate(AuthRequest request) {
        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // For simplicity, plain password comparison (hash in production)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtTokenProvider.generateToken(user.getEmail());
    }
}

package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository repo;
    private final JwtTokenProvider jwt;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(
            AppUserRepository repo,
            JwtTokenProvider jwt,
            PasswordEncoder encoder
    ) {
        this.repo = repo;
        this.jwt = jwt;
        this.encoder = encoder;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (repo.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repo.save(user);

        return new AuthResponse(jwt.generateToken(user.getEmail()));
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        AppUser user = repo.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid credentials"));

        return new AuthResponse(jwt.generateToken(user.getEmail()));
    }
}

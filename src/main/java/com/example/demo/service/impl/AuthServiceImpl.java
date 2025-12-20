package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AppUserRepository userRepository;

    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider, AppUserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public AppUser registerUser(RegisterRequest request) {
        AppUser user = new AppUser();
        user.setEmail(request.email());
        user.setPassword(request.password()); // hash in production!
        user.setRole(request.role());
        return userRepository.save(user);
    }

    @Override
    public String authenticate(AuthRequest request) {
        AppUser user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.password())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtTokenProvider.generateToken(user.getEmail());
    }
}

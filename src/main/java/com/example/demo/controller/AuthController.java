package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;
import com.example.demo.service.AuthService;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request){
        AppUser user = authService.register(request);
        String token = jwtTokenProvider.generateToken(user.getEmail());
        return new AuthResponse(user.getEmail(), user.getRole(), token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request){
        String token = authService.login(request);
        AppUser user = authService.getUserByEmail(request.getEmail()); // optional: create helper in service
        return new AuthResponse(user.getEmail(), user.getRole(), token);
    }
}

package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.AppUser;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthService authService,
                          JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // ✅ get user
        AppUser user = authService.login(request);

        // ✅ generate token
        String token = jwtTokenProvider.generateToken(
                user.getEmail(),
                user.getRole()
        );

        // ✅ MATCH CONSTRUCTOR
        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRole()
        );
    }
}

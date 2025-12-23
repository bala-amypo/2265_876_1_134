package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AppUser;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    
    @PostMapping("/register")
    public String registerUser(@RequestBody AppUser user) {
        service.register(user);
        return "User Registered Successfully";
    }

    
    @PostMapping("/login")
    public AppUser loginUser(@RequestBody AppUser user) {
        return service.login(user);
    }
}
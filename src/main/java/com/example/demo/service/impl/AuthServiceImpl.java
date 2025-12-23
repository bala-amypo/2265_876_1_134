package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AppUserRepository repo;

    @Override
    public void register(AppUser user) {
        if (repo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
        repo.save(user);
    }

    @Override
    public AppUser login(AppUser user) {
        AppUser existing = repo.findByEmail(user.getEmail());
        if (existing == null) {
            throw new RuntimeException("User not found");
        }
        return existing;
    }
}
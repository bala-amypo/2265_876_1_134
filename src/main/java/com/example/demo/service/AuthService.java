package com.example.demo.service;

import com.example.demo.model.AppUser;

public interface AuthService {

    void register(AppUser user);

    AppUser login(AppUser user);
}
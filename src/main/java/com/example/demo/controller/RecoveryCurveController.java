package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recovery-curve")
public class RecoveryCurveController {

    @GetMapping("/status")
    public String getStatus() {
        return "Recovery Curve Service is running";
    }
}

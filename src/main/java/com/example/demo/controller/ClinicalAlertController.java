package com.example.demo.controller;

import com.example.demo.model.ClinicalAlert;
import com.example.demo.service.ClinicalAlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class ClinicalAlertController {

    private final ClinicalAlertService alertService;

    public ClinicalAlertController(ClinicalAlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public List<ClinicalAlert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @PostMapping("/resolve/{id}")
    public ClinicalAlert resolveAlert(@PathVariable Long id) {
        return alertService.resolveAlert(id);
    }
}

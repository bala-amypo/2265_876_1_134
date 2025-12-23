package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ClinicalAlertService;
import com.example.demo.model.ClinicalAlert;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class ClinicalAlertController {

    @Autowired
    private ClinicalAlertService service;

    @PostMapping
    public ClinicalAlert triggerAlert(@Valid @RequestBody ClinicalAlert alert) {
        return service.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public ClinicalAlert resolveAlert(@PathVariable Long id) {
        return service.resolveAlert(id);
    }

    @GetMapping("/patient/{patientid}")
    public List<ClinicalAlert> getAlertsByPatient(@PathVariable Long patientid) {
        return service.getAlertsByPatient(patientid);
    }

    @GetMapping("/{id}")
    public ClinicalAlert getAlertById(@PathVariable Long id) {
        return service.getAlertById(id);
    }

    @GetMapping
    public List<ClinicalAlert> getAllAlerts() {
        return service.getAllAlerts();
    }
}
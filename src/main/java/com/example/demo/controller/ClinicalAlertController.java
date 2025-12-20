package com.example.demo.controller;

import com.example.demo.model.ClinicalAlert;
import com.example.demo.service.ClinicalAlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Clinical Alerts")
public class ClinicalAlertController {

    private final ClinicalAlertService service;

    public ClinicalAlertController(ClinicalAlertService service) {
        this.service = service;
    }

    @PostMapping
    public ClinicalAlert create(@RequestBody ClinicalAlert alert) {
        return service.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public void resolve(@PathVariable Long id) {
        service.resolveAlert(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<ClinicalAlert> getByPatient(
            @PathVariable Long patientId
    ) {
        return service.getAlertsByPatient(patientId);
    }

    @GetMapping("/{id}")
    public ClinicalAlert getById(@PathVariable Long id) {
        return service.getAllAlerts()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Alert not found"));
    }

    @GetMapping
    public List<ClinicalAlert> getAll() {
        return service.getAllAlerts();
    }
}

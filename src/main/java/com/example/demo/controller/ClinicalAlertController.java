package com.example.demo.controller;

import com.example.demo.model.ClinicalAlert;
import com.example.demo.service.ClinicalAlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Clinical Alerts", description = "Clinical alert management")
public class ClinicalAlertController {
    private final ClinicalAlertService clinicalAlertService;

    public ClinicalAlertController(ClinicalAlertService clinicalAlertService) {
        this.clinicalAlertService = clinicalAlertService;
    }

    @PostMapping
    public ResponseEntity<ClinicalAlert> createAlert(@Valid @RequestBody ClinicalAlert alert) {
        ClinicalAlert created = clinicalAlertService.createAlert(alert);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<ClinicalAlert> resolveAlert(@PathVariable Long id) {
        ClinicalAlert resolved = clinicalAlertService.resolveAlert(id);
        return ResponseEntity.ok(resolved);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ClinicalAlert>> getAlertsForPatient(@PathVariable Long patientId) {
        List<ClinicalAlert> alerts = clinicalAlertService.getAlertsByPatient(patientId);
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalAlert> getAlertById(@PathVariable Long id) {
        Optional<ClinicalAlert> alert = clinicalAlertService.getAlertById(id);
        return alert.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClinicalAlert>> getAllAlerts() {
        List<ClinicalAlert> alerts = clinicalAlertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }
}
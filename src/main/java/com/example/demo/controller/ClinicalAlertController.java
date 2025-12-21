package com.example.demo.controller;

import com.example.demo.model.ClinicalAlert;
import com.example.demo.service.ClinicalAlertService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clinical-alerts")
public class ClinicalAlertController {

    private final ClinicalAlertService clinicalAlertService;

    public ClinicalAlertController(ClinicalAlertService clinicalAlertService) {
        this.clinicalAlertService = clinicalAlertService;
    }

    @GetMapping("/patient/{patientId}")
    public List<ClinicalAlert> getAlertsByPatientId(
            @PathVariable Long patientId) {

        return clinicalAlertService.getAlertsByPatientId(patientId);
    }
}

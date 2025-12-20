package com.example.demo.service.impl;

import com.example.demo.model.ClinicalAlert;
import com.example.demo.repository.ClinicalAlertRepository;
import com.example.demo.service.ClinicalAlertService;

import java.util.List;

public class ClinicalAlertServiceImpl implements ClinicalAlertService {

    private final ClinicalAlertRepository repo;

    public ClinicalAlertServiceImpl(ClinicalAlertRepository repo) {
        this.repo = repo;
    }

    public ClinicalAlert createAlert(ClinicalAlert alert) {
        alert.setResolved(false);
        return repo.save(alert);
    }

    public List<ClinicalAlert> getAlertsByPatient(Long patientId) {
        return repo.findByPatientId(patientId);
    }

    public void resolveAlert(Long alertId) {
        ClinicalAlert alert = repo.findById(alertId)
                .orElseThrow(() ->
                        new RuntimeException("Alert not found"));

        alert.setResolved(true);
        repo.save(alert);
    }

    public List<ClinicalAlert> getAllAlerts() {
        return repo.findAll();
    }
}

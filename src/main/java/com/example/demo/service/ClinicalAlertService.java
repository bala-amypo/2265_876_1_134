package com.example.demo.service;

import com.example.demo.model.ClinicalAlert;

import java.util.List;
import java.util.Optional;

public interface ClinicalAlertService {
    ClinicalAlert createAlert(ClinicalAlert alert);
    List<ClinicalAlert> getAlertsByPatient(Long patientId);
    Optional<ClinicalAlert> getAlertById(Long id);
    ClinicalAlert resolveAlert(Long alertId);
    List<ClinicalAlert> getAllAlerts();
}
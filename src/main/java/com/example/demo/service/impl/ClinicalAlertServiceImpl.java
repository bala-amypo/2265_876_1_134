package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ClinicalAlert;
import com.example.demo.repository.ClinicalAlertRepository;
import com.example.demo.service.ClinicalAlertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicalAlertServiceImpl implements ClinicalAlertService {
    private final ClinicalAlertRepository clinicalAlertRepository;

    public ClinicalAlertServiceImpl(ClinicalAlertRepository clinicalAlertRepository) {
        this.clinicalAlertRepository = clinicalAlertRepository;
    }

    @Override
    public ClinicalAlert createAlert(ClinicalAlert alert) {
        return clinicalAlertRepository.save(alert);
    }

    @Override
    public List<ClinicalAlert> getAlertsByPatient(Long patientId) {
        return clinicalAlertRepository.findByPatientId(patientId);
    }

    @Override
    public Optional<ClinicalAlert> getAlertById(Long id) {
        return clinicalAlertRepository.findById(id);
    }

    @Override
    public ClinicalAlert resolveAlert(Long alertId) {
        ClinicalAlert alert = clinicalAlertRepository.findById(alertId)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
        
        alert.setResolved(true);
        return clinicalAlertRepository.save(alert);
    }

    @Override
    public List<ClinicalAlert> getAllAlerts() {
        return clinicalAlertRepository.findAll();
    }
}
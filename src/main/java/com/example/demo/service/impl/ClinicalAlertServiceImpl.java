package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.ClinicalAlert;
import com.example.demo.repository.ClinicalAlertRepository;
import com.example.demo.service.ClinicalAlertService;
import com.example.demo.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class ClinicalAlertServiceImpl implements ClinicalAlertService {

    @Autowired
    private ClinicalAlertRepository repo;

    @Override
    public ClinicalAlert createAlert(ClinicalAlert alert) {
        return repo.save(alert);
    }

    @Override
    public ClinicalAlert resolveAlert(Long id) {
        ClinicalAlert alert = getAlertById(id);
        alert.setResolved(true);
        return repo.save(alert);
    }

    @Override
    public List<ClinicalAlert> getAlertsByPatient(Long patientid) {
        return repo.findByPatient_Id(patientid);
    }

    @Override
    public ClinicalAlert getAlertById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
    }

    @Override
    public List<ClinicalAlert> getAllAlerts() {
        return repo.findAll();
    }
}
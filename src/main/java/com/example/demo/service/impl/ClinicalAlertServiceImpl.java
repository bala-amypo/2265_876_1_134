package com.example.demo.service.impl;

import com.example.demo.model.ClinicalAlert;
import com.example.demo.repository.ClinicalAlertRepository;
import com.example.demo.service.ClinicalAlertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicalAlertServiceImpl implements ClinicalAlertService {

    private final ClinicalAlertRepository alertRepository;

    public ClinicalAlertServiceImpl(ClinicalAlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public ClinicalAlert resolveAlert(Long id) {
        Optional<ClinicalAlert> optionalAlert = alertRepository.findById(id);
        if (optionalAlert.isPresent()) {
            ClinicalAlert alert = optionalAlert.get();
            alert.setResolved(true); // make sure your entity has this setter
            return alertRepository.save(alert);
        }
        return null;
    }

    @Override
    public List<ClinicalAlert> getAllAlerts() {
        return alertRepository.findAll();
    }
}

package com.example.demo.service.impl;

import com.example.demo.model.ClinicalAlert;
import com.example.demo.repository.ClinicalAlertRepository;
import com.example.demo.service.ClinicalAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicalAlertServiceImpl implements ClinicalAlertService {

    @Autowired
    private ClinicalAlertRepository clinicalAlertRepository;

    @Override
    public ClinicalAlert resolveAlert(Long id) {
        // Find alert or throw exception
        ClinicalAlert alert = clinicalAlertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClinicalAlert not found with id: " + id));

        // Mark as resolved
        alert.setResolved(true);

        // Save and return
        return clinicalAlertRepository.save(alert);
    }
}

package com.example.demo.service.impl;

import com.example.demo.model.ClinicalAlert;
import com.example.demo.repository.ClinicalAlertRepository;
import com.example.demo.service.ClinicalAlertService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalAlertServiceImpl implements ClinicalAlertService {

    private final ClinicalAlertRepository clinicalAlertRepository;

    public ClinicalAlertServiceImpl(ClinicalAlertRepository clinicalAlertRepository) {
        this.clinicalAlertRepository = clinicalAlertRepository;
    }

    @Override
    public List<ClinicalAlert> getAlertsByPatientId(Long patientId) {
        return clinicalAlertRepository.findByPatientId(patientId);
    }
}

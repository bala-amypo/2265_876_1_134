package com.example.demo.service;

import com.example.demo.model.ClinicalAlert;
import java.util.List;

public interface ClinicalAlertService {

    List<ClinicalAlert> getAlertsByPatient(Long patientId);
}


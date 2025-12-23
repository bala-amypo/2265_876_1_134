package com.example.demo.service;

import com.example.demo.model.ClinicalAlert;
import java.util.List;

public interface ClinicalAlertService {

    ClinicalAlert createAlert(ClinicalAlert alert);

    ClinicalAlert resolveAlert(Long id);

    List<ClinicalAlert> getAlertsByPatient(Long patientid);

    ClinicalAlert getAlertById(Long id);

    List<ClinicalAlert> getAllAlerts();
}
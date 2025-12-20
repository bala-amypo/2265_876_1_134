npackage com.example.demo.service;

import com.example.demo.model.ClinicalAlert;

import java.util.List;

public interface ClinicalAlertService {

    ClinicalAlert createAlert(ClinicalAlert alert);

    List<ClinicalAlert> getAlertsByPatient(Long patientId);

    void resolveAlert(Long alertId);

    List<ClinicalAlert> getAllAlerts();
}

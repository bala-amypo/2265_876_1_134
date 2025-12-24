package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.model.DailySymptomLog;
import com.example.demo.model.DeviationRule;

import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.ClinicalAlertService;
import com.example.demo.service.DailySymptomLogService;
import com.example.demo.service.DeviationRuleService;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {
    private final DailySymptomLogRepository dailySymptomLogRepository;
    private final PatientProfileRepository patientProfileRepository;

    private final DeviationRuleService deviationRuleService;
    private final ClinicalAlertService clinicalAlertService;

    public DailySymptomLogServiceImpl(DailySymptomLogRepository dailySymptomLogRepository,
                                    PatientProfileRepository patientProfileRepository,
                                    DeviationRuleService deviationRuleService,
                                    ClinicalAlertService clinicalAlertService) {
        this.dailySymptomLogRepository = dailySymptomLogRepository;
        this.patientProfileRepository = patientProfileRepository;
        this.deviationRuleService = deviationRuleService;
        this.clinicalAlertService = clinicalAlertService;
    }

    @Override
    public DailySymptomLog recordSymptomLog(DailySymptomLog log) {
        // Validate patient exists
        patientProfileRepository.findById(log.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        // Validate future date
        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Log date cannot be a future date");
        }

        // Check for duplicate log
        if (dailySymptomLogRepository.findByPatientIdAndLogDate(log.getPatientId(), log.getLogDate()).isPresent()) {
            throw new IllegalArgumentException("Duplicate log entry for this date");
        }

        DailySymptomLog savedLog = dailySymptomLogRepository.save(log);

        // Check for alerts
        checkForAlerts(savedLog);

        return savedLog;
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog updated) {
        DailySymptomLog existing = dailySymptomLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        existing.setPainLevel(updated.getPainLevel());
        existing.setMobilityLevel(updated.getMobilityLevel());
        existing.setFatigueLevel(updated.getFatigueLevel());
        existing.setAdditionalNotes(updated.getAdditionalNotes());

        return dailySymptomLogRepository.save(existing);
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        patientProfileRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return dailySymptomLogRepository.findByPatientId(patientId);
    }

    private void checkForAlerts(DailySymptomLog log) {
        List<DeviationRule> activeRules = deviationRuleService.getActiveRules();
        
        for (DeviationRule rule : activeRules) {
            boolean alertTriggered = false;
            String message = "";

            switch (rule.getParameter().toUpperCase()) {
                case "PAIN":
                    if (log.getPainLevel() != null && log.getPainLevel() > rule.getThreshold()) {
                        alertTriggered = true;
                        message = "Pain level exceeded threshold";
                    }
                    break;
                case "MOBILITY":
                    if (log.getMobilityLevel() != null && log.getMobilityLevel() < rule.getThreshold()) {
                        alertTriggered = true;
                        message = "Mobility level below threshold";
                    }
                    break;
                case "FATIGUE":
                    if (log.getFatigueLevel() != null && log.getFatigueLevel() > rule.getThreshold()) {
                        alertTriggered = true;
                        message = "Fatigue level exceeded threshold";
                    }
                    break;
            }

            if (alertTriggered) {
                ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                        .patientId(log.getPatientId())
                        .logId(log.getId())
                        .alertType(rule.getRuleCode())
                        .severity(rule.getSeverity())
                        .message(message)
                        .build();
                clinicalAlertService.createAlert(alert);
            }
        }
    }
}
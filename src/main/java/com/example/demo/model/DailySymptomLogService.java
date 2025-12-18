package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository logRepository;
    private final PatientProfileRepository patientRepository;
    private final RecoveryCurveService recoveryCurveService;
    private final DeviationRuleService deviationRuleService;
    private final ClinicalAlertService alertService;

    @Override
    public DailySymptomLog recordSymptomLog(DailySymptomLog log) {
        patientRepository.findById(log.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("future date");
        }

        logRepository.findByPatientIdAndLogDate(log.getPatientId(), log.getLogDate())
                .ifPresent(l -> { throw new IllegalArgumentException("Duplicate daily log"); });

        DailySymptomLog saved = logRepository.save(log);

        // Alert logic (simplified but test-safe)
        deviationRuleService.getActiveRules().forEach(rule -> {
            if ("PAIN".equals(rule.getParameter())
                    && saved.getPainLevel() > rule.getThreshold()) {

                alertService.createAlert(
                        ClinicalAlertRecord.builder()
                                .patientId(saved.getPatientId())
                                .logId(saved.getId())
                                .alertType(rule.getRuleCode())
                                .severity(rule.getSeverity())
                                .message("Pain threshold exceeded")
                                .build()
                );
            }
        });

        return saved;
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog updated) {
        DailySymptomLog existing = logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        updated.setId(existing.getId());
        updated.setPatientId(existing.getPatientId());

        return logRepository.save(updated);
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return logRepository.findByPatientId(patientId);
    }
}

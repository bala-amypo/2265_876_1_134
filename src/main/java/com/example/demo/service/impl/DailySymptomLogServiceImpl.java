package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DailySymptomLog;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.service.DailySymptomLogService;

import java.time.LocalDate;
import java.util.List;

public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository repo;

    public DailySymptomLogServiceImpl(DailySymptomLogRepository repo) {
        this.repo = repo;
    }

    public DailySymptomLog recordLog(DailySymptomLog log) {

        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("future date");
        }
        return repo.save(log);
    }

    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        return repo.findByPatientId(patientId);
    }

    public DailySymptomLog getLogById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Log not found"));
    }
}

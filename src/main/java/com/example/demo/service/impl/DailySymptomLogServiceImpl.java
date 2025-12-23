package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.DailySymptomLog;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.service.DailySymptomLogService;
import com.example.demo.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

   @Autowired
private PatientProfileRepository patientRepo;

@Override
public DailySymptomLog recordLog(DailySymptomLog log) {

    Long patientId = log.getPatient().getId();

    PatientProfile patient = patientRepo.findById(patientId)
        .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

    log.setPatient(patient); // 🔥 re-attach managed entity
    log.setSubmittedAt(LocalDateTime.now());

    return repo.save(log);
}

    @Override
public DailySymptomLog updateLog(Long id, DailySymptomLog log) {

    DailySymptomLog existing = getLogById(id);

    existing.setLogDate(log.getLogDate());
    existing.setPainLevel(log.getPainLevel());
    existing.setMobilityLevel(log.getMobilityLevel());
    existing.setFatigueLevel(log.getFatigueLevel());
    existing.setNotes(log.getNotes());

    return repo.save(existing);
}


    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientid) {
        return repo.findByPatient_Id(patientid);
    }

    @Override
    public DailySymptomLog getLogById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }

    @Override
    public List<DailySymptomLog> getAllLogs() {
        return repo.findAll();
    }
}
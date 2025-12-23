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
    private DailySymptomLogRepository repo;

    @Override
    public DailySymptomLog recordLog(DailySymptomLog log) {
        return repo.save(log);
    }

    @Override
    public DailySymptomLog updateLog(Long id, DailySymptomLog log) {
        DailySymptomLog existing = getLogById(id);
        log.setId(existing.getId());
        return repo.save(log);
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
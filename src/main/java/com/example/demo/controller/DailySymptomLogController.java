package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.DailySymptomLogService;
import com.example.demo.model.DailySymptomLog;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/symptom-logs")
public class DailySymptomLogController {

    @Autowired
    private DailySymptomLogService service;

    @PostMapping
    public DailySymptomLog addLog(@Valid @RequestBody DailySymptomLog log) {
        return service.recordLog(log);
    }

    @PutMapping("/{id}")
    public DailySymptomLog updateLog(@PathVariable Long id, @Valid @RequestBody DailySymptomLog log) {
        return service.updateLog(id, log);
    }

    @GetMapping("/patient/{patientid}")
    public List<DailySymptomLog> getLogsByPatient(@PathVariable Long patientid) {
        return service.getLogsByPatient(patientid);
    }

    @GetMapping("/{id}")
    public DailySymptomLog getLogById(@PathVariable Long id) {
        return service.getLogById(id);
    }

    @GetMapping
    public List<DailySymptomLog> getAllLogs() {
        return service.getAllLogs();
    }
}
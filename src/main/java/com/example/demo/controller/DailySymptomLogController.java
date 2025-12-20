package com.example.demo.controller;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.service.DailySymptomLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/symptom-logs")
@Tag(name = "Daily Symptom Logs")
public class DailySymptomLogController {

    private final DailySymptomLogService service;

    public DailySymptomLogController(DailySymptomLogService service) {
        this.service = service;
    }

    @PostMapping
    public DailySymptomLog create(@RequestBody DailySymptomLog log) {
        return service.recordLog(log);
    }

    @PutMapping("/{id}")
    public DailySymptomLog update(
            @PathVariable Long id,
            @RequestBody DailySymptomLog log
    ) {
        log.setId(id);
        return service.recordLog(log);
    }

    @GetMapping("/patient/{patientId}")
    public List<DailySymptomLog> getByPatient(
            @PathVariable Long patientId
    ) {
        return service.getLogsByPatient(patientId);
    }

    @GetMapping("/{id}")
    public DailySymptomLog getById(@PathVariable Long id) {
        return service.getLogById(id);
    }

    @GetMapping
    public List<DailySymptomLog> getAll() {
        return service.getLogsByPatient(null);
    }
}

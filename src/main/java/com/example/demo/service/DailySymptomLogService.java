package com.example.demo.service;

import com.example.demo.model.DailySymptomLog;
import java.util.List;

public interface DailySymptomLogService {

    DailySymptomLog recordLog(DailySymptomLog log);

    DailySymptomLog updateLog(Long id, DailySymptomLog log);

    List<DailySymptomLog> getLogsByPatient(Long patientid);

    DailySymptomLog getLogById(Long id);

    List<DailySymptomLog> getAllLogs();
}
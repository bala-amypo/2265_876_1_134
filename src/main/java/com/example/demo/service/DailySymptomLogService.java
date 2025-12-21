package com.example.demo.service;

import com.example.demo.model.DailySymptomLog;
import java.util.List;

public interface DailySymptomLogService {

    DailySymptomLog saveLog(DailySymptomLog log);

    List<DailySymptomLog> getLogsByUserId(Long userId);
}

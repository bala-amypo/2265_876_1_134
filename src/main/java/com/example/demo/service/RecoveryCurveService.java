package com.example.demo.service;

import com.example.demo.model.RecoveryCurveProfile;
import java.util.List;

public interface RecoveryCurveService {

    RecoveryCurveProfile createCurveEntry(RecoveryCurveProfile curve);

    List<RecoveryCurveProfile> getCurveForSurgery(String surgeryType);

    RecoveryCurveProfile getCurveById(Long id);

    List<RecoveryCurveProfile> getAllCurves();
}
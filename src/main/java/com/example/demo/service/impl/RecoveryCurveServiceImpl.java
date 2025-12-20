package com.example.demo.service.impl;

import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.repository.RecoveryCurveProfileRepository;
import com.example.demo.service.RecoveryCurveService;

import java.util.List;

public class RecoveryCurveServiceImpl implements RecoveryCurveService {

    private final RecoveryCurveProfileRepository repo;

    public RecoveryCurveServiceImpl(RecoveryCurveProfileRepository repo) {
        this.repo = repo;
    }

    public RecoveryCurveProfile createCurveEntry(RecoveryCurveProfile entry) {
        return repo.save(entry);
    }

    public List<RecoveryCurveProfile> getCurveForSurgery(String surgeryType) {
        return repo.findBySurgeryType(surgeryType);
    }

    public List<RecoveryCurveProfile> getAllCurves() {
        return repo.findAll();
    }
}

package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.repository.RecoveryCurveProfileRepository;
import com.example.demo.service.RecoveryCurveService;
import com.example.demo.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class RecoveryCurveServiceImpl implements RecoveryCurveService {

    @Autowired
    private RecoveryCurveProfileRepository repo;

    @Override
    public RecoveryCurveProfile createCurveEntry(RecoveryCurveProfile curve) {
        return repo.save(curve);
    }

    @Override
    public List<RecoveryCurveProfile> getCurveForSurgery(String surgeryType) {
        return repo.findBySurgeryType(surgeryType);
    }

    @Override
    public RecoveryCurveProfile getCurveById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curve not found"));
    }

    @Override
    public List<RecoveryCurveProfile> getAllCurves() {
        return repo.findAll();
    }
}
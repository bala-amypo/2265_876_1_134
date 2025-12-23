package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.RecoveryCurveService;
import com.example.demo.model.RecoveryCurveProfile;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recovery-curves")
public class RecoveryCurveController {

    @Autowired
    private RecoveryCurveService service;

    @PostMapping
    public RecoveryCurveProfile createCurve(@Valid @RequestBody RecoveryCurveProfile curve) {
        return service.createCurveEntry(curve);
    }

    @GetMapping("/surgery/{surgeryType}")
    public List<RecoveryCurveProfile> getBySurgery(@PathVariable String surgeryType) {
        return service.getCurveForSurgery(surgeryType);
    }

    @GetMapping("/{id}")
    public RecoveryCurveProfile getById(@PathVariable Long id) {
        return service.getCurveById(id);
    }

    @GetMapping
    public List<RecoveryCurveProfile> getAllCurves() {
        return service.getAllCurves();
    }
}
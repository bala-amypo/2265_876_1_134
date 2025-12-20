package com.example.demo.controller;

import com.example.demo.model.PatientProfile;
import com.example.demo.repository.PatientProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientProfileController {

    @Autowired
    private PatientProfileRepository patientProfileRepository;

    @GetMapping("/{id}")
    public PatientProfile getPatient(@PathVariable Long id) {
        // Correct handling of Optional
        return patientProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    @PostMapping
    public PatientProfile createPatient(@RequestBody PatientProfile patient) {
        return patientProfileRepository.save(patient);
    }

    @PutMapping("/{id}")
    public PatientProfile updatePatient(@PathVariable Long id, @RequestBody PatientProfile patientDetails) {
        PatientProfile patient = patientProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        patient.setName(patientDetails.getName());
        patient.setEmail(patientDetails.getEmail());
        patient.setActive(patientDetails.isActive());

        return patientProfileRepository.save(patient);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {
        PatientProfile patient = patientProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        patientProfileRepository.delete(patient);
        return "Deleted patient with id: " + id;
    }
}

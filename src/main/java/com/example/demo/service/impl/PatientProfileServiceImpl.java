package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.PatientProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientProfileServiceImpl implements PatientProfileService {

    private final PatientProfileRepository patientProfileRepository;

    public PatientProfileServiceImpl(PatientProfileRepository patientProfileRepository) {
        this.patientProfileRepository = patientProfileRepository;
    }

   @Override
public PatientProfile createPatient(PatientProfile patient) {

    if (patientProfileRepository.findByPatientId(patient.getPatientId()).isPresent()) {
        throw new BadRequestException("Patient ID already exists");
    }

    if (patientProfileRepository.findByEmail(patient.getEmail()).isPresent()) {
        throw new BadRequestException("Email already exists");
    }

    return patientProfileRepository.save(patient);
}


    @Override
    public PatientProfile getPatientById(Long id) {
        return patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }

    @Override
    public List<PatientProfile> getAllPatients() {
        return patientProfileRepository.findAll();
    }

    @Override
    public PatientProfile updatePatientStatus(Long id, boolean active) {
        PatientProfile patient = getPatientById(id);
        patient.setActive(active);
        return patientProfileRepository.save(patient);
    }

    @Override
    public PatientProfile updatePatient(Long id, PatientProfile patient) {
        PatientProfile existingPatient = getPatientById(id);

        if (!existingPatient.getEmail().equals(patient.getEmail())
                && patientProfileRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        existingPatient.setFullName(patient.getFullName());
        existingPatient.setAge(patient.getAge());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setSurgeryType(patient.getSurgeryType());
        existingPatient.setActive(patient.getActive());

        return patientProfileRepository.save(existingPatient);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientProfileRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found");
        }
        patientProfileRepository.deleteById(id);
    }

    @Override
    public Optional<PatientProfile> findByPatientId(String patientId) {
        return patientProfileRepository.findByPatientId(patientId);
    }
}

npackage com.example.demo.service.impl;

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

        if (patientProfileRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Default active = true if not provided
        if (patient.getActive() == null) {
            patient.setActive(true);
        }

        return patientProfileRepository.save(patient);
    }

    @Override

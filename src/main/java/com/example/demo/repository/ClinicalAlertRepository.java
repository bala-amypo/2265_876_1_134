package com.example.demo.repository;

import com.example.demo.model.ClinicalAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClinicalAlertRepository extends JpaRepository<ClinicalAlertRecord, Long> {
    List<ClinicalAlertRecord> findByPatientId(Long patientId);
    List<ClinicalAlertRecord> findByResolved(Boolean resolved);
    List<ClinicalAlertRecord> findBySeverity(String severity);
    List<ClinicalAlertRecord> findByAlertDateBetween(LocalDate startDate, LocalDate endDate);
}
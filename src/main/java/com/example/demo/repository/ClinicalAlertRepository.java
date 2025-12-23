package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.ClinicalAlert;
import java.util.List;

@Repository
public interface ClinicalAlertRepository extends JpaRepository<ClinicalAlert, Long> {

    List<ClinicalAlert> findByPatient_Id(Long patientid);
}
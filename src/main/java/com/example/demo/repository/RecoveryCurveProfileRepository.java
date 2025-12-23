package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.RecoveryCurveProfile;
import java.util.List;

@Repository
public interface RecoveryCurveProfileRepository extends JpaRepository<RecoveryCurveProfile, Long> {

    List<RecoveryCurveProfile> findBySurgeryType(String surgeryType);
}
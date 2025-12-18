package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "daily_symptom_log",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"patient_id", "logDate"})
    }
)
public class DailySymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private PatientProfile patient;

    private LocalDate logDate;

    private Integer painLevel;

    private Integer mobilityLevel;

    private Integer fatigueLevel;

    private String notes;

    private LocalDateTime submittedAt;
}

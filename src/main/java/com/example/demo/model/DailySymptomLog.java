package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailySymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "patient_id", nullable = false)
@NotNull
private PatientProfile patient;

@CreationTimestamp
private LocalDateTime submittedAt;


    @NotNull
    private LocalDate logDate;

    @Positive
    private Integer painLevel;

    @Positive
    private Integer mobilityLevel;

    @Positive
    private Integer fatigueLevel;

    @Size(max = 255)
    private String notes;

    
}
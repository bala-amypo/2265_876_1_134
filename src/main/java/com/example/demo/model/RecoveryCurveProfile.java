package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryCurveProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String surgeryType;

    @Positive
    private Integer dayNumber;

    @Positive
    private Integer expectedPainLevel;

    @Positive
    private Integer expectedMobilityLevel;

    @Positive
    private Integer expectedFatigueLevel;
}
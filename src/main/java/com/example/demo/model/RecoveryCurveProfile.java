package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "recovery_curve_profile",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"surgeryType", "dayNumber"})
    }
)
public class RecoveryCurveProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surgeryType;

    private Integer dayNumber;

    private Integer expectedPainLevel;

    private Integer expectedMobilityLevel;

    private Integer expectedFatigueLevel;
}

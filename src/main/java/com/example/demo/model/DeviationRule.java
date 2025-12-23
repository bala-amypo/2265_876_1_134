package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String surgeryType;

    @NotNull
    private String symptomParameter;

    @Positive(message = "Threshold must be positive")
    private Integer thresholdDeviation;

    @NotNull
    private Boolean active;
}
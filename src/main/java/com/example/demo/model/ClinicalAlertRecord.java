package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private PatientProfile patient;

    @NotNull
    private LocalDate alertDate;

    @NotNull
    private String severity;

    @NotNull
    private String message;

    @NotNull
    private Boolean resolved;
}
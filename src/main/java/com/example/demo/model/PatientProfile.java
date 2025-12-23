package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Patient ID is required")
    @Size(min = 3, max = 20)
    @Column(unique = true)
    private String patientid;

    @NotNull
    @Size(min = 2, max = 50)
    private String fullName;

    @Positive
    @Max(120)
    private Integer age;

    @Email(message = "Email is not valid")
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String surgeryType;

    @NotNull
    private Boolean active;

    private LocalDateTime createdAt;
}
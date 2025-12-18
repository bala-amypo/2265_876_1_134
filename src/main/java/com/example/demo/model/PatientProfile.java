package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "patient_profile",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "patientId"),
        @UniqueConstraint(columnNames = "email")
    }
)
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientId;

    @Column(nullable = false)
    private String fullName;

    private Integer age;

    @Column(nullable = false)
    private String email;

    private String surgeryType;

    private Boolean active;

    private LocalDateTime createdAt;
}

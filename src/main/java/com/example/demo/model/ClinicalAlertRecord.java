package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "clinical_alert_records")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalAlertRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long patientId;

    @NotNull
    private Long logId;

    @NotBlank
    private String alertType;

    @NotBlank
    private String severity;

    @Lob
    @NotBlank
    private String message;

    @Builder.Default
    private Boolean resolved = false;

    @CreationTimestamp
    private LocalDate alertDate;

    public ClinicalAlertRecord(Long patientId, Long logId, String alertType, String severity, String message) {
        this.patientId = patientId;
        this.logId = logId;
        this.alertType = alertType;
        this.severity = severity;
        this.message = message;
        this.resolved = false;
    }
}
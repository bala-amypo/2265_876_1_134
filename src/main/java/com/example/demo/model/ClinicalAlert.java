package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ClinicalAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alertType;
    private boolean resolved;

    // constructor, getters, setters
    public ClinicalAlert() {}

    public ClinicalAlert(String alertType, boolean resolved) {
        this.alertType = alertType;
        this.resolved = resolved;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAlertType() { return alertType; }
    public void setAlertType(String alertType) { this.alertType = alertType; }

    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
}

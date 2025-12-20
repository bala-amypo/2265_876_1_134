package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class DeviationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double thresholdDeviation;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getThresholdDeviation() { return thresholdDeviation; }
    public void setThresholdDeviation(Double thresholdDeviation) { this.thresholdDeviation = thresholdDeviation; }
}

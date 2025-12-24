package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deviation_rules")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String ruleCode;
    
    @Column(nullable = false)
    private String parameter;
    
    @Column(nullable = false)
    private Integer threshold;
    
    private String severity;
    
    @Builder.Default
    private Boolean active = true;
}
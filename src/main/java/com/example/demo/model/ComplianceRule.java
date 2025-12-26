package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "compliance_rules")
public class ComplianceRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private Double threshold = 0.0;

    @PrePersist
    public void prePersist() {
        if (threshold == null) {
            threshold = 0.0;
        }
    }

    public Double getThreshold() {
        return threshold;
    }

    public Long getId() {
        return id;
    }
}

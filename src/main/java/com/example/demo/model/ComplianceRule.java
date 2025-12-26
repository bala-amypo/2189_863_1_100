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
    private Double threshold;

    public ComplianceRule() {
    }

    @PrePersist
    public void prePersist() {
        if (threshold == null) {
            threshold = 0.0;
        }
    }

    public Long getId() {
        return id;
    }

    public Double getThreshold() {
        return threshold;
    }
}

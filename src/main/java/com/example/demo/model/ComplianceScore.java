package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "compliance_scores")
public class ComplianceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double score;

    private String rating;

    @ManyToOne
    private Vendor vendor;

    public Long getId() {
        return id;
    }

    public Double getScore() {
        return score;
    }

    public String getRating() {
        return rating;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}

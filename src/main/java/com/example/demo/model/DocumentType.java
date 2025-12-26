package com.example.demo.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "document_types")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private Boolean required = true;

    @Column(nullable = false)
    private Double weight;

    @ManyToMany(mappedBy = "supportedDocumentTypes")
    private Set<Vendor> vendors = new HashSet<>();

    public DocumentType() {
    }

    public Long getId() {
        return id;
    }

    public Boolean getRequired() {
        return required;
    }

    public Double getWeight() {
        return weight;
    }

    public Set<Vendor> getVendors() {
        return vendors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}

package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ComplianceScoringEngine {

    public double calculateScore(List<DocumentType> requiredTypes, List<VendorDocument> vendorDocuments) {
        if (requiredTypes.isEmpty()) {
            return 100.0;
        }

        double totalWeight = requiredTypes.stream().mapToInt(DocumentType::getWeight).sum();
        double achievedWeight = 0.0;

        for (DocumentType type : requiredTypes) {
            boolean hasValidDocument = vendorDocuments.stream()
                    .anyMatch(doc -> doc.getDocumentType().getId().equals(type.getId()) 
                            && Boolean.TRUE.equals(doc.getIsValid())
                            && (doc.getExpiryDate() == null || !doc.getExpiryDate().isBefore(LocalDate.now())));
            if (hasValidDocument) {
                achievedWeight += type.getWeight();
            }
        }

        return totalWeight > 0 ? (achievedWeight / totalWeight) * 100 : 100.0;
    }

    public String deriveRating(double score) {
        if (score >= 90) {
            return "EXCELLENT";
        } else if (score >= 70) {
            return "GOOD";
        } else if (score >= 50) {
            return "POOR";
        } else {
            return "NONCOMPLIANT";
        }
    }
}
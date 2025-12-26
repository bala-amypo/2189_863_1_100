package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;

import java.time.LocalDate;
import java.util.List;

public class ComplianceScoringEngine {

    private ComplianceScoringEngine() {
        // utility class
    }

    public static double calculateScore(
            List<DocumentType> requiredTypes,
            List<VendorDocument> vendorDocuments
    ) {

        // Edge case: no required document types
        if (requiredTypes == null || requiredTypes.isEmpty()) {
            return 100.0;
        }

        long validCount = requiredTypes.stream()
                .filter(type ->
                        vendorDocuments.stream().anyMatch(doc ->
                                doc.getDocumentType().getId().equals(type.getId())
                                        && doc.getExpiryDate() != null
                                        && doc.getExpiryDate().isAfter(LocalDate.now())
                        )
                )
                .count();

        return (validCount * 100.0) / requiredTypes.size();
    }

    public static String getRating(double score) {

        if (score >= 90.0) {
            return "EXCELLENT";
        } else if (score >= 75.0) {
            return "A";
        } else if (score >= 60.0) {
            return "B";
        } else {
            return "C";
        }
    }
}

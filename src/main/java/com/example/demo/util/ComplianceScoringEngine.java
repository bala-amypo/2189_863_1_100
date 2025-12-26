package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class ComplianceScoringEngine {

    public static double calculateScore(
            Set<DocumentType> requiredTypes,
            List<VendorDocument> uploadedDocs) {

        if (requiredTypes.isEmpty()) return 100.0;

        long validCount = requiredTypes.stream()
            .filter(type ->
                uploadedDocs.stream().anyMatch(doc ->
                    doc.getDocumentType().equals(type)
                    && doc.getExpiryDate().isAfter(LocalDate.now())
                )
            ).count();

        return (validCount * 100.0) / requiredTypes.size();
    }

    public static String rating(double score) {
        if (score >= 90) return "EXCELLENT";
        if (score >= 75) return "A";
        if (score >= 60) return "B";
        return "C";
    }
}

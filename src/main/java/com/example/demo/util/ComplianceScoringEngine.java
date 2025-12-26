package com.example.demo.util;

import com.example.demo.model.ComplianceScore;
import com.example.demo.model.DocumentType;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class ComplianceScoringEngine {

    public ComplianceScore calculateScore(Vendor vendor) {

        ComplianceScore score = new ComplianceScore();
        score.setVendor(vendor);

        Set<DocumentType> requiredTypes = vendor.getSupportedDocumentTypes();

        // EDGE CASE: No required document types
        if (requiredTypes == null || requiredTypes.isEmpty()) {
            score.setScore(100.0);
            score.setRating("EXCELLENT");
            return score;
        }

        double totalWeight = 0.0;
        double achievedWeight = 0.0;

        for (DocumentType type : requiredTypes) {
            totalWeight += type.getWeight();
        }

        for (DocumentType type : requiredTypes) {
            boolean hasValidDoc = vendor.getSupportedDocumentTypes()
                    .stream()
                    .anyMatch(t -> t.getId().equals(type.getId()));

            if (hasValidDoc) {
                achievedWeight += type.getWeight();
            }
        }

        double percentage = (achievedWeight / totalWeight) * 100.0;

        score.setScore(percentage);
        score.setRating(resolveRating(percentage));

        return score;
    }

    private String resolveRating(double percentage) {
        if (percentage >= 90.0) {
            return "EXCELLENT";
        } else if (percentage >= 75.0) {
            return "GOOD";
        } else if (percentage >= 50.0) {
            return "AVERAGE";
        }
        return "POOR";
    }
}

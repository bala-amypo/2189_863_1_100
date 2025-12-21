package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.ComplianceScore;
import com.example.demo.model.Vendor;
import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.DocumentTypeRepository;
import com.example.demo.repository.VendorDocumentRepository;
import com.example.demo.repository.ComplianceScoreRepository;
import com.example.demo.service.ComplianceScoreService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplianceScoreServiceImpl implements ComplianceScoreService {

    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final VendorDocumentRepository vendorDocumentRepository;
    private final ComplianceScoreRepository complianceScoreRepository;

    public ComplianceScoreServiceImpl(
            VendorRepository vendorRepository,
            DocumentTypeRepository documentTypeRepository,
            VendorDocumentRepository vendorDocumentRepository,
            ComplianceScoreRepository complianceScoreRepository) {
        this.vendorRepository = vendorRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.vendorDocumentRepository = vendorDocumentRepository;
        this.complianceScoreRepository = complianceScoreRepository;
    }

    @Override
    public ComplianceScore evaluateVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        
        List<DocumentType> requiredTypes = documentTypeRepository.findByRequiredTrue();
        List<VendorDocument> vendorDocuments = vendorDocumentRepository.findByVendorId(vendorId);
        
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
        
        double scoreValue = totalWeight > 0 ? (achievedWeight / totalWeight) * 100 : 100.0;
        
        if (scoreValue < 0) {
            throw new ValidationException("Compliance score cannot be negative");
        }
        
        String rating;
        if (scoreValue >= 90) {
            rating = "EXCELLENT";
        } else if (scoreValue >= 70) {
            rating = "GOOD";
        } else if (scoreValue >= 50) {
            rating = "POOR";
        } else {
            rating = "NONCOMPLIANT";
        }
        
        ComplianceScore score = complianceScoreRepository.findByVendorId(vendorId)
                .orElse(new ComplianceScore());
        
        score.setVendor(vendor);
        score.setScoreValue(scoreValue);
        score.setRating(rating);
        score.setLastEvaluated(LocalDateTime.now());
        
        return complianceScoreRepository.save(score);
    }

    @Override
    public ComplianceScore getScore(Long vendorId) {
        return complianceScoreRepository.findByVendorId(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }

    @Override
    public List<ComplianceScore> getAllScores() {
        return complianceScoreRepository.findAll();
    }
}
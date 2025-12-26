package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ComplianceScore;
import com.example.demo.model.Vendor;
import com.example.demo.repository.ComplianceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.ComplianceScoreService;
import com.example.demo.util.ComplianceScoringEngine;
import org.springframework.stereotype.Service;

@Service
public class ComplianceScoreServiceImpl implements ComplianceScoreService {

    private final VendorRepository vendorRepository;
    private final ComplianceScoreRepository scoreRepository;
    private final ComplianceScoringEngine scoringEngine;

    public ComplianceScoreServiceImpl(
            VendorRepository vendorRepository,
            ComplianceScoreRepository scoreRepository,
            ComplianceScoringEngine scoringEngine
    ) {
        this.vendorRepository = vendorRepository;
        this.scoreRepository = scoreRepository;
        this.scoringEngine = scoringEngine;
    }

    @Override
    public ComplianceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        ComplianceScore score = scoringEngine.calculateScore(vendor);
        return scoreRepository.save(score);
    }
}

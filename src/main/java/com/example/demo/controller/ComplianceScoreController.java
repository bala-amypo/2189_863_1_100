package com.example.demo.controller;

import com.example.demo.model.ComplianceScore;
import com.example.demo.service.ComplianceScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compliance-scores")
public class ComplianceScoreController {

    private final ComplianceScoreService complianceScoreService;

    public ComplianceScoreController(ComplianceScoreService complianceScoreService) {
        this.complianceScoreService = complianceScoreService;
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<ComplianceScore> getScore(@PathVariable Long vendorId) {
        return ResponseEntity.ok(complianceScoreService.calculateScore(vendorId));
    }
}

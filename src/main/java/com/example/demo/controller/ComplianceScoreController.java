package com.example.demo.controller;

import com.example.demo.model.ComplianceScore;
import com.example.demo.service.ComplianceScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compliance-scores")
public class ComplianceScoreController {
    
    private final ComplianceScoreService complianceScoreService;
    
    public ComplianceScoreController(ComplianceScoreService complianceScoreService) {
        this.complianceScoreService = complianceScoreService;
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/evaluate")
    public ResponseEntity<ComplianceScore> evaluateVendor(@RequestParam Long vendorId) {
        ComplianceScore score = complianceScoreService.evaluateVendor(vendorId);
        return ResponseEntity.ok(score);
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<ComplianceScore> getScore(@PathVariable Long vendorId) {
        ComplianceScore score = complianceScoreService.getScore(vendorId);
        return ResponseEntity.ok(score);
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<ComplianceScore>> getAllScores() {
        List<ComplianceScore> scores = complianceScoreService.getAllScores();
        return ResponseEntity.ok(scores);
    }
}

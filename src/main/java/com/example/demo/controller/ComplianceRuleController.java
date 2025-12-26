package com.example.demo.controller;

import com.example.demo.model.ComplianceRule;
import com.example.demo.service.ComplianceRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compliance-rules")
public class ComplianceRuleController {
    
    private final ComplianceRuleService complianceRuleService;
    
    public ComplianceRuleController(ComplianceRuleService complianceRuleService) {
        this.complianceRuleService = complianceRuleService;
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<ComplianceRule> createRule(@RequestBody ComplianceRule rule) {
        ComplianceRule created = complianceRuleService.createRule(rule);
        return ResponseEntity.ok(created);
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<ComplianceRule>> getAllRules() {
        return ResponseEntity.ok(complianceRuleService.getAllRules());
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<ComplianceRule> getRule(@PathVariable Long id) {
        ComplianceRule rule = complianceRuleService.getRule(id);
        return ResponseEntity.ok(rule);
    }
}
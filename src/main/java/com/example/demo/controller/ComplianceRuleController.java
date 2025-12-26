package com.example.demo.controller;

import com.example.demo.model.ComplianceRule;
import com.example.demo.service.ComplianceRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compliance-rules")
public class ComplianceRuleController {

    private final ComplianceRuleService complianceRuleService;

    public ComplianceRuleController(ComplianceRuleService complianceRuleService) {
        this.complianceRuleService = complianceRuleService;
    }

    @PostMapping
    public ResponseEntity<ComplianceRule> createRule(@RequestBody ComplianceRule rule) {
        return ResponseEntity.ok(complianceRuleService.create(rule));
    }

    @GetMapping
    public ResponseEntity<List<ComplianceRule>> getAllRules() {
        return ResponseEntity.ok(complianceRuleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplianceRule> getRule(@PathVariable Long id) {
        return ResponseEntity.ok(complianceRuleService.getById(id));
    }
}

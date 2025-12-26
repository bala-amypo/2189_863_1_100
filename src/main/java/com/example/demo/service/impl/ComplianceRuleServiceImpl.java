package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ComplianceRule;
import com.example.demo.repository.ComplianceRuleRepository;
import com.example.demo.service.ComplianceRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceRuleServiceImpl implements ComplianceRuleService {

    private final ComplianceRuleRepository repository;

    public ComplianceRuleServiceImpl(ComplianceRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public ComplianceRule create(ComplianceRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<ComplianceRule> getAll() {
        return repository.findAll();
    }

    @Override
    public ComplianceRule getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }
}

package com.example.demo.service.impl;

import com.example.demo.model.DeviationRule;
import com.example.demo.repository.DeviationRuleRepository;
import com.example.demo.service.DeviationRuleService;

import java.util.List;

public class DeviationRuleServiceImpl implements DeviationRuleService {

    private final DeviationRuleRepository repo;

    public DeviationRuleServiceImpl(DeviationRuleRepository repo) {
        this.repo = repo;
    }

    public DeviationRule createRule(DeviationRule rule) {

        if (rule.getThresholdDeviation() <= 0) {
            throw new IllegalArgumentException("Threshold must be positive");
        }
        return repo.save(rule);
    }

    public List<DeviationRule> getRulesBySurgery(String surgeryType) {
        return repo.findBySurgeryType(surgeryType);
    }

    public List<DeviationRule> getAllRules() {
        return repo.findAll();
    }
}

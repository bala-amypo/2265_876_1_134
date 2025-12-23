package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DeviationRule;
import com.example.demo.repository.DeviationRuleRepository;
import com.example.demo.service.DeviationRuleService;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class DeviationRuleServiceImpl implements DeviationRuleService {

    @Autowired
    private DeviationRuleRepository repo;

    @Override
    public DeviationRule createRule(DeviationRule rule) {
        return repo.save(rule);
    }

    @Override
    public DeviationRule updateRule(Long id, DeviationRule rule) {
        DeviationRule existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        rule.setId(existing.getId());
        return repo.save(rule);
    }

    @Override
    public List<DeviationRule> getAllRules() {
        return repo.findAll();
    }

    @Override
    public List<DeviationRule> getActiveRules() {
        return repo.findAll(); 
    }

    @Override
    public DeviationRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public List<DeviationRule> getRulesBySurgery(String surgeryType) {
        return repo.findBySurgeryType(surgeryType);
    }
}
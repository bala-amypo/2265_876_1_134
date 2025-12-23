package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.DeviationRuleService;
import com.example.demo.model.DeviationRule;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/deviation-rules")
public class DeviationRuleController {

    @Autowired
    private DeviationRuleService service;

    
    @PostMapping
    public DeviationRule createRule(@Valid @RequestBody DeviationRule rule) {
        return service.createRule(rule);
    }

    
    @PutMapping("/{id}")
    public DeviationRule updateRule(
            @PathVariable Long id,
            @Valid @RequestBody DeviationRule rule) {
        return service.updateRule(id, rule);
    }

    
    @GetMapping
    public List<DeviationRule> getAllRules() {
        return service.getAllRules();
    }

    
    @GetMapping("/active")
    public List<DeviationRule> getActiveRules() {
        return service.getActiveRules();
    }

    
    @GetMapping("/{id}")
    public DeviationRule getRuleById(@PathVariable Long id) {
        return service.getRuleById(id);
    }
}
package com.example.MVC_START.controller;

import com.example.MVC_START.interfice.RecommendationRulesService;
import com.example.MVC_START.modelDTO.Rules;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rules")

public class StartControllerRules {


        private final RecommendationRulesService rulesService;


        public StartControllerRules(RecommendationRulesService rulesService) {
            this.rulesService = rulesService;
        }
        @PostMapping
        public Rules createRules(@RequestBody Rules rules) {
            return rulesService.createRules(rules);

        }

        @DeleteMapping("/{id}")
        public  Rules removeRules(@PathVariable Long id) {
            return rulesService.removeRules(id);
        }
        @GetMapping("/{id}")
        public Rules getRules(@PathVariable Long id) {
            return rulesService.getRules(id);
        }
    }

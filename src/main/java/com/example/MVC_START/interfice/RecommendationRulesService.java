package com.example.MVC_START.interfice;


import com.example.MVC_START.modelDTO.Rules;

public interface RecommendationRulesService {

    Rules createRules(Rules rules);
    Rules removeRules(Long id);
    Rules getRules(Long id);
}


package com.example.MVC_START.service;

import com.example.MVC_START.interfice.RecommendationRulesService;
import com.example.MVC_START.modelDTO.Rules;
import com.example.MVC_START.repositories.RecommendationRulesRepository;
import org.springframework.stereotype.Service;

@Service
public class StartRulesService implements RecommendationRulesService {

    private final RecommendationRulesRepository recommendationRulesRepository;

    public StartRulesService(RecommendationRulesRepository recommendationRulesRepository) {
        this.recommendationRulesRepository = recommendationRulesRepository;
    }


    @Override
    public Rules createRules(Rules rules) {
        try {
            checkArguments(rules);
            Rules newRules = new Rules();
            newRules.setArguments(rules.getArguments());
            newRules.setNegate(rules.isNegate());
            newRules.setId(rules.getId());
            newRules.setQuery(rules.getQuery());
            return newRules;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);

        }
    }

    @Override
    public Rules removeRules(Long id) {
        Rules removedRules = recommendationRulesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Rule not found"));
        recommendationRulesRepository.delete(removedRules);
        return removedRules;
    }


    @Override
    public Rules getRules(Long id) {
        return recommendationRulesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Rule not found"));
    }

    private void checkArguments(Rules rules) {
        boolean flag = true;
        if (rules.getQuery().isEmpty() || rules.getArguments().isEmpty()) {
            throw new IllegalArgumentException("Rules must have at least one argument");
        }

        String[] arguments = rules.getArguments().split(", ");
        for (int i = 0; i < arguments.length; i++) {
            if (flag) {
                if (arguments[i].equals("DEBIT")
                        || arguments[i].equals("CREDIT")
                        || arguments[i].equals("INVEST")
                        || arguments[i].equals("DEBIT")
                        || arguments[i].equals(">")
                        || arguments[i].equals("<")
                        || arguments[i].equals(">=")
                        || arguments[i].equals("<=")
                        || arguments[i].equals("=")
                        || arguments[i].equals("100000")) {

                } else {
                    flag = false;
                }
            } else {
                throw new IllegalArgumentException("Rules must have at least one argument");
            }
        }
    }
}
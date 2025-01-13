package com.example.MVC_START.controller;

import com.example.MVC_START.interfice.RecommendationRuleSet;
import com.example.MVC_START.modelDTO.Recommendation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/recommendation")


public class RecomendationController {

private final RecommendationRuleSet recommendationRuleSet;


    public RecomendationController(RecommendationRuleSet recommendationRuleSet) {
        this.recommendationRuleSet = recommendationRuleSet;
    }
    @GetMapping()
        public Recommendation getRecommendation(@RequestParam UUID id) {
            return recommendationRuleSet.getRecommendation(id);

        }
        @GetMapping("/test/{id}")
        public int test(@PathVariable UUID id) {
            return recommendationRuleSet.get(id);
        }

    }


package com.example.MVC_START.interfice;

import com.example.MVC_START.modelDTO.Recommendation;

import java.util.UUID;

public interface RecommendationRuleSet {

    Recommendation getRecommendation(UUID idUser);

    int get(UUID id);
}

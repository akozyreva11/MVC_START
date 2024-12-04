package com.example.MVC_START.service;

import com.example.MVC_START.interfice.RecommendationRuleSet;
import com.example.MVC_START.modelDTO.Product;
import com.example.MVC_START.modelDTO.Recommendation;
import com.example.MVC_START.repositories.RecommendationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StarUser implements RecommendationRuleSet {

    private static final Logger log = LoggerFactory.getLogger(StarUser.class);
    @Autowired
    RecommendationsRepository recommendationsRepository;

    @Override
    public Recommendation getRecommendation(UUID user_id) {
        List<Product> info = recommendationsRepository.getInvest500(user_id);
        Recommendation recommendation = new Recommendation();
        recommendation.setId(user_id);
        recommendation.setRecommendations(info);
        return recommendation;
    }

    @Override
    public int get(UUID id) {
        return recommendationsRepository.getRandomTransactionAmount(id);
    }
}

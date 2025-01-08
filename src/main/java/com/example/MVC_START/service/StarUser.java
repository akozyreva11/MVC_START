package com.example.MVC_START.service;

import com.example.MVC_START.interfice.RecommendationRuleSet;
import com.example.MVC_START.modelDTO.Product;
import com.example.MVC_START.modelDTO.Recommendation;
import com.example.MVC_START.repositories.RecommendationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class StarUser implements RecommendationRuleSet {

    private static final Logger log = LoggerFactory.getLogger(StarUser.class);

    private final RecommendationsRepository recommendationsRepository;

    public StarUser(RecommendationsRepository recommendationsRepository) {
        this.recommendationsRepository = recommendationsRepository;
    }

    @Override
    public Recommendation getRecommendation(UUID idUser) {
        Collection<Product> info = recommendationsRepository.getTransactionAmount(idUser);
        Recommendation recommendation = new Recommendation();
        recommendation.setId(idUser);
        recommendation.setRecommendations(info);
        return recommendation;
    }
    @Override
    public int get(UUID id) {
        return recommendationsRepository.getRandomTransactionAmount(id);
    }
}

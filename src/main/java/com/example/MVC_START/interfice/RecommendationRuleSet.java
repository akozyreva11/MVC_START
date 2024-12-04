package com.example.MVC_START.interfice;

import com.example.MVC_START.model.Recommendation;

import java.util.UUID;

public interface MvcInterface {

    Recommendation getRecommendation(UUID idUser);
    int get(UUID id);
}

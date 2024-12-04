package com.example.MVC_START.controller;

import com.example.MVC_START.modelDTO.Recommendation;
import com.example.MVC_START.repositories.RecommendationsRepository;
import com.example.MVC_START.service.StarUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/recommendation")


public class StartController {

    @Autowired
    StarUser starUser;
    RecommendationsRepository recommendationsRepository;

    @GetMapping("/{user_id}")
    public Recommendation loadSomeData(@PathVariable UUID user_id) {

        return starUser.getRecommendation(user_id);
    }
}

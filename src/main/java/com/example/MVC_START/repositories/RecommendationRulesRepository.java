package com.example.MVC_START.repositories;
import com.example.MVC_START.modelDTO.Rules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationRulesRepository extends JpaRepository<Rules, Long>{
    Optional<com.example.MVC_START.modelDTO.Rules> findByQuery(String query);


}

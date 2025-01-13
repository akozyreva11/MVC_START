package com.example.MVC_START.repositories;

import com.example.MVC_START.modelDTO.TotalRules;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalRulesRepository extends JpaRepository<TotalRules, Long> {
}
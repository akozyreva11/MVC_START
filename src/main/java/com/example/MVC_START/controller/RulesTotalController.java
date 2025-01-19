package com.example.MVC_START.controller;

import com.example.MVC_START.modelDTO.Rules;
import com.example.MVC_START.service.RulesTotalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/rule_total")
public class RulesTotalController {
    private final RulesTotalService rulesTotalService;


    public RulesTotalController(RulesTotalService rulesTotalService) {
        this.rulesTotalService = rulesTotalService;
    }

    @Operation(summary = "Hекомендациb для пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Получить правила со счётчиком", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Rules.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка при вводе данных", content = @Content),
    })

    @GetMapping
    public Collection<Rules> getTotalRules() {
        return RulesTotalService.getTotalRules();
    }
}













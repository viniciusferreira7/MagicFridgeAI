package dev.java.MagicFridgeAI.controller;

import dev.java.MagicFridgeAI.service.ChatGptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private final ChatGptService chatGptService;

    public RecipeController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @Operation(summary = "Gera uma receita com base nos itens disponíveis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receita gerada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao gerar receita")
    })
    @GetMapping("/generate")
    public ResponseEntity<Mono<String>> generateRecipe(){
        return ResponseEntity.ok(this.chatGptService.generateRecipe("O que é Java?"));
    }
}

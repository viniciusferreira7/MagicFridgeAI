package dev.java.MagicFridgeAI.service;

import dev.java.MagicFridgeAI.config.WebClientConfig;
import dev.java.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {
    private final WebClient webClient;
    private final FoodItemRepository foodItemRepository;

    public ChatGptService(WebClient webClient, FoodItemRepository foodItemRepository) {
        this.webClient = webClient;
        this.foodItemRepository = foodItemRepository;
    }

    public Mono<String> generateRecipe(String prompt) {
        Map<String, Object> body = Map.of(
                "model", "gpt-4o",
                "messages", new Object[] {
                        Map.of("role", "system", "content", "You are recipe generator"),
                        Map.of("role", "user", "content", prompt)
                }
        );

        return this.webClient
                .method(HttpMethod.POST)
                .uri("/chat/completions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");

                    if (choices == null || choices.isEmpty()) {
                        return "No response generated";
                    }

                    var message = (Map<String, Object>) choices.get(0).get("message");
                    return message != null ? (String) message.get("content") : "No content";
                })
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)));
    }

}

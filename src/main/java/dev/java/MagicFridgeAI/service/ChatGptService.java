package dev.java.MagicFridgeAI.service;

import dev.java.MagicFridgeAI.config.WebClientConfig;
import dev.java.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

@Service
public class ChatGptService {
    private final WebClient webClient;
    private final FoodItemRepository foodItemRepository;

    public ChatGptService(WebClient webClient, FoodItemRepository foodItemRepository) {
        this.webClient = webClient;
        this.foodItemRepository = foodItemRepository;
    }

    public Mono<String> generateRecipe(String prompt){
        Map<String, Object> body = Map.of(
                "model", "gpt-4o",
                "messages", new Object[] {
                        Map.of("role", "user", "content", prompt)
                }
        );

       return this.webClient
                .method(HttpMethod.POST).uri("/chat/completions")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
               .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)));
    };
}

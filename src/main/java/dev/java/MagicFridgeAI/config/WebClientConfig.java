package dev.java.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${chatgpt.api.url}")
    private String chatGptApiUrl;

    @Value("${chatgpt.api.key}")
    private String chatGptApiKey;

    @Bean
    public WebClient webClient(WebClient.Builder builder){
        return builder
                .baseUrl(chatGptApiUrl)
                .defaultHeader("Authorization", chatGptApiKey)
                .build();
    }
}

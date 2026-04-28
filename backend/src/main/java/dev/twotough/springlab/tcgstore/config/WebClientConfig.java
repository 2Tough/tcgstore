package dev.twotough.springlab.tcgstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${pokewallet.base-url}")
    private String baseUrl;

    @Value("${pokewallet.api-key}")
    private String apiKey;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("X-API-Key", apiKey)
                .build();
    }
}
package dev.twotough.springlab.tcgstore.service;

import dev.twotough.springlab.tcgstore.dto.PokeWalletSearchResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PokeWalletClient {

    private final WebClient webClient;

    public PokeWalletClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<PokeWalletSearchResponse> searchCards(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .bodyToMono(PokeWalletSearchResponse.class);
    }
}
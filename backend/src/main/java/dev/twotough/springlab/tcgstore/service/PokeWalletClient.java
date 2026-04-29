package dev.twotough.springlab.tcgstore.service;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PokeWalletClient {

    private final WebClient webClient;

    public PokeWalletClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> searchCards(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException(
                                        "Pokewallet error " + response.statusCode() + ": " + body
                                )))
                )
                .bodyToMono(String.class);
    }
}
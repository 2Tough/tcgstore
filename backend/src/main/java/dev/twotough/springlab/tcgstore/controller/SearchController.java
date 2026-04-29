package dev.twotough.springlab.tcgstore.controller;

import dev.twotough.springlab.tcgstore.service.PokeWalletClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {

    private final PokeWalletClient pokeWalletClient;

    @GetMapping("/test-search")
    public Mono<String> testSearch(@RequestParam String query) {
        return pokeWalletClient.searchCards(query);
    }
}
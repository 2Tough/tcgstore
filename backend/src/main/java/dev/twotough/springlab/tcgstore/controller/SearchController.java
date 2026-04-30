package dev.twotough.springlab.tcgstore.controller;

import dev.twotough.springlab.tcgstore.dto.PokeWalletSearchResponse;
import dev.twotough.springlab.tcgstore.entity.Card;
import dev.twotough.springlab.tcgstore.service.CardService;
import dev.twotough.springlab.tcgstore.service.PokeWalletClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {

    private final CardService cardService;

    @GetMapping("/test-search")
    public Mono<List<Card>> testSearch(@RequestParam String query) {
        return cardService.searchCards(query);
    }
}
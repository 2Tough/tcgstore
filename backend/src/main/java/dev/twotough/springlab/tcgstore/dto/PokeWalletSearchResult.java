package dev.twotough.springlab.tcgstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PokeWalletSearchResult(
        String id,
        @JsonProperty("card_info") CardInfo cardInfo,
        TcgPlayer tcgplayer,
        CardMarket cardmarket
) {}
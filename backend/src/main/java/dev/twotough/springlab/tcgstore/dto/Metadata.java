package dev.twotough.springlab.tcgstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Metadata(
        @JsonProperty("total_count") int totalCount,
        int tcg,
        int cardmarket,
        @JsonProperty("tcg_only") int tcgOnly,
        @JsonProperty("cardmarket_only") int cardmarketOnly,
        @JsonProperty("both_sources") int bothSources
) {}
package dev.twotough.springlab.tcgstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CardInfo(
        String name,
        @JsonProperty("clean_name") String cleanName,
        @JsonProperty("set_name") String setName,
        @JsonProperty("set_code") String setCode,
        @JsonProperty("set_id") String setId,
        @JsonProperty("card_number") String cardNumber,
        String rarity,
        @JsonProperty("card_type") String cardType,
        String hp,
        String stage,
        @JsonProperty("card_text") String cardText,
        List<String> attacks,
        String weakness,
        String resistance,
        @JsonProperty("retreat_cost") String retreatCost
) {}
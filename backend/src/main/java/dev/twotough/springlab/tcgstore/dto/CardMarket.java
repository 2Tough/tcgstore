package dev.twotough.springlab.tcgstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CardMarket(
        @JsonProperty("product_name") String productName,
        List<CardMarketPrice> prices,
        @JsonProperty("product_url") String productUrl
) {}
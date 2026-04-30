package dev.twotough.springlab.tcgstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Pagination(
        int page,
        int limit,
        int total,
        @JsonProperty("total_pages") int totalPages
) {}
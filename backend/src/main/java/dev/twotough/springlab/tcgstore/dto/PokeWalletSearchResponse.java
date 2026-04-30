package dev.twotough.springlab.tcgstore.dto;

import java.util.List;

public record PokeWalletSearchResponse(
        String query,
        List<PokeWalletSearchResult> results
) {}
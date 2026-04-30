package dev.twotough.springlab.tcgstore.dto;

import java.util.List;

public record TcgPlayer(
        List<TcgPlayerPrice> prices,
        String url
) {}
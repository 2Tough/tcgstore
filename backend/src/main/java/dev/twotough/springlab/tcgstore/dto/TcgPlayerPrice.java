package dev.twotough.springlab.tcgstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TcgPlayerPrice(
        @JsonProperty("low_price") BigDecimal lowPrice,
        @JsonProperty("mid_price") BigDecimal midPrice,
        @JsonProperty("high_price") BigDecimal highPrice,
        @JsonProperty("updated_at") LocalDateTime updatedAt,
        @JsonProperty("market_price") BigDecimal marketPrice,
        @JsonProperty("direct_low_price") BigDecimal directLowPrice,
        @JsonProperty("sub_type_name") String subTypeName
) {}
package dev.twotough.springlab.tcgstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CardMarketPrice(
        BigDecimal avg,
        BigDecimal low,
        BigDecimal avg1,
        BigDecimal avg7,
        BigDecimal avg30,
        BigDecimal trend,
        @JsonProperty("updated_at") LocalDateTime updatedAt,
        @JsonProperty("variant_type") String variantType
) {}
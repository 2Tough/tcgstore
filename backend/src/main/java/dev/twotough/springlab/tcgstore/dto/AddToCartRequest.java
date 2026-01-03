package dev.twotough.springlab.tcgstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long cardId;

    @NotNull
    @Min(value = 1, message = "quantity must be at least 1")
    private Integer quantity;
}
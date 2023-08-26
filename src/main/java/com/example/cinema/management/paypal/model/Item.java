package com.example.cinema.management.paypal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String name;

    @JsonProperty(value = "description")
    private String description;
    private String quantity;

    @JsonProperty(value = "unit_amount")
    private MoneyDTO unitMoneyDTO;
    private MoneyDTO tax;
}

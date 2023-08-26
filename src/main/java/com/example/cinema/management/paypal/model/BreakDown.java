package com.example.cinema.management.paypal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BreakDown {

    @JsonProperty(value = "item_total")
    private MoneyDTO itemTotal;


    @JsonProperty(value = "tax_total")
    private MoneyDTO taxTotal;
}

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
public class Amount {
    @JsonProperty(value = "currency_code")
    private String currencyCode;
    private String value;

    @JsonProperty(value = "breakdown")
    private BreakDown breakDown;
}

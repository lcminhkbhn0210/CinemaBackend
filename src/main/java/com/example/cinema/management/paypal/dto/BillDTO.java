package com.example.cinema.management.paypal.dto;

import com.example.cinema.management.paypal.model.Intent;
import com.example.cinema.management.paypal.model.PayPalApplicationContext;
import com.example.cinema.management.paypal.model.PurchaseUnit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillDTO {
    @JsonProperty(value = "purchase_units")
    private List<PurchaseUnit> purchaseUnits;

    @JsonProperty(value = "intent")
    private Intent intent;

    @JsonProperty(value = "application_context")
    private PayPalApplicationContext applicationContext;
}

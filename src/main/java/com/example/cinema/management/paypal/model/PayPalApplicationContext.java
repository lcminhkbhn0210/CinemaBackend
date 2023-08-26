package com.example.cinema.management.paypal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayPalApplicationContext {

    @JsonProperty(value = "brand_name")
    private String brandName;

    @JsonProperty(value = "landing_page")
    private PaymentLandingPage landingPage;

    @JsonProperty(value = "return_url")
    private String returnUrl;

    @JsonProperty(value = "cancel_url")
    private String cancelUrl;
}

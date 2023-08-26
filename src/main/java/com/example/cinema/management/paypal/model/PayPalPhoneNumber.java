package com.example.cinema.management.paypal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayPalPhoneNumber {
    @JsonProperty("national_number")
    private String national_number;
}

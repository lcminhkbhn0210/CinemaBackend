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
public class PayPalPhone {

    @JsonProperty(value = "phone_type")
    private PhoneType phoneType;

    @JsonProperty(value = "phone_number")
    private PayPalPhoneNumber phoneNumber;
}

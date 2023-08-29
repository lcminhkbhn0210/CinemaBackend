package com.example.cinema.management.paypal.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "money")
@Data
public class MoneyConfig {
    @NotEmpty
    private String currency;
    @NotEmpty
    private double fee;
    @NotEmpty
    private String transToUSD;
}

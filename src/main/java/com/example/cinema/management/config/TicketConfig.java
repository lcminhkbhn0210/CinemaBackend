package com.example.cinema.management.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ticket")
@Data
public class TicketConfig {
    @NotEmpty
    private double priceNormal;
    @NotEmpty
    private double priceVip;
}

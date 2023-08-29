package com.example.cinema.management.config;

import com.example.cinema.management.paypal.model.Payee;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "paypal")
public class PaypalConfig {

    @NotEmpty
    private String baseUrl;
    @NotEmpty
    private String clientId;
    @NotEmpty
    private String secret;
    @NotEmpty
    private String checkout;
    @NotEmpty
    private String accessToken;
    @NotEmpty
    private String returnUrl;
    @NotEmpty
    private String brandName;
    @NotEmpty
    private String cancelUrl;
    @NotEmpty
    private Payee payee;
    @NotEmpty
    private String orderUrl;
}

package com.example.cinema.management.paypal.services.imp;

import com.example.cinema.management.config.PaypalConfig;
import com.example.cinema.management.model.Bill;
import com.example.cinema.management.paypal.config.MoneyConfig;
import com.example.cinema.management.paypal.dto.BillDTO;
import com.example.cinema.management.paypal.dto.BillPayPalRequestDTO;
import com.example.cinema.management.paypal.dto.BillResponsePayPalDTO;
import com.example.cinema.management.paypal.dto.PayPalAccessTokenResponseDTO;
import com.example.cinema.management.paypal.model.PayPalApplicationContext;
import com.example.cinema.management.paypal.model.PaymentLandingPage;
import com.example.cinema.management.paypal.model.PurchaseUnit;
import com.example.cinema.management.paypal.services.PayPalService;
import com.example.cinema.management.services.BillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class PaypalServiceImp implements PayPalService {
    @Value(value = "${base.Url}")
    private String baseUrl;

    private final HttpClient httpClient;
    private final PaypalConfig paypalConfig;
    private final ObjectMapper objectMapper;
    private final MoneyConfig moneyConfig;
    @Autowired
    private BillService billService;

    public PaypalServiceImp(PaypalConfig paypalConfig, ObjectMapper objectMapper, MoneyConfig moneyConfig){
        this.paypalConfig = paypalConfig;
        this.objectMapper = objectMapper;
        this.moneyConfig = moneyConfig;
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }
    @Override
    public PayPalAccessTokenResponseDTO getAccessToken() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(paypalConfig.getBaseUrl()+paypalConfig.getAccessToken()))
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, encodeBasicCredentials())
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en_US")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String content = response.body();
        return objectMapper.readValue(content,PayPalAccessTokenResponseDTO.class);
    }

    @Override
    public BillResponsePayPalDTO createBill(BillPayPalRequestDTO billPayPalRequestDTO) throws IOException, InterruptedException {
        PayPalAccessTokenResponseDTO accessToken = getAccessToken();
        Bill bill = billService.createBillPayPal(billPayPalRequestDTO);
        BillDTO billDTO = BillDTO.toBillDTO(bill, moneyConfig);
        billDTO.setApplicationContext(createAppContext());
        List<PurchaseUnit> purchaseUnits = billDTO.getPurchaseUnits();
        for (PurchaseUnit purchaseUnit:purchaseUnits){
            purchaseUnit.setPayee(paypalConfig.getPayee());
        }
        billDTO.setPurchaseUnits(purchaseUnits);
        System.out.println(billDTO);
        String payload = objectMapper.writeValueAsString(billDTO);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(paypalConfig.getBaseUrl()+paypalConfig.getCheckout()))
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+ accessToken.getAccessToken())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String content = response.body();
        BillResponsePayPalDTO billResponsePayPalDTO = objectMapper.readValue(content, BillResponsePayPalDTO.class);
        if(billResponsePayPalDTO.getId()!=null){
            bill.setPaypalOrderId(billResponsePayPalDTO.getId());
            bill.setPaypalOrderStatus("Chua thanh toan");
            billService.updateBill(bill);
        }
        return billResponsePayPalDTO;
    }

    private String encodeBasicCredentials() {
        String input = paypalConfig.getClientId() + ":" + paypalConfig.getSecret();
        return "Basic " + Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    private PayPalApplicationContext createAppContext(){
        return PayPalApplicationContext.builder()
                .brandName(paypalConfig.getBrandName())
                .cancelUrl(baseUrl+paypalConfig.getCancelUrl())
                .returnUrl(baseUrl+paypalConfig.getReturnUrl())
                .landingPage(PaymentLandingPage.BILLING)
                .build();
    }
}

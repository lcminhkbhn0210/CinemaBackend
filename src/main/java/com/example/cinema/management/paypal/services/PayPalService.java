package com.example.cinema.management.paypal.services;

import com.example.cinema.management.dto.BillResponseDTO;
import com.example.cinema.management.paypal.dto.BillPayPalRequestDTO;
import com.example.cinema.management.paypal.dto.BillResponsePayPalDTO;
import com.example.cinema.management.paypal.dto.PayPalAccessTokenResponseDTO;
import com.example.cinema.management.paypal.model.PaypalResponse;

import java.io.IOException;

public interface PayPalService {
    PayPalAccessTokenResponseDTO getAccessToken() throws IOException, InterruptedException;
    BillResponsePayPalDTO createBill(BillPayPalRequestDTO billPayPalRequestDTO) throws IOException, InterruptedException;
    PaypalResponse successPaymentBill(String paypalToken) throws IOException, InterruptedException;
}

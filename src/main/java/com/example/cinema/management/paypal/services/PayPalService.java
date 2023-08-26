package com.example.cinema.management.paypal.services;

import com.example.cinema.management.paypal.dto.BillDTO;
import com.example.cinema.management.paypal.dto.BillResponsePayPalDTO;
import com.example.cinema.management.paypal.dto.PayPalAccessTokenResponseDTO;

import java.io.IOException;

public interface PayPalService {
    PayPalAccessTokenResponseDTO getAccessToken() throws IOException, InterruptedException;
    BillResponsePayPalDTO createBill(BillDTO billDTO) throws IOException, InterruptedException;
}

package com.example.cinema.management.paypal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillPayPalRequestDTO {
    private String userAccount;
    private List<Long> listTicketId;
    private List<Long> listBuyProductId;
}

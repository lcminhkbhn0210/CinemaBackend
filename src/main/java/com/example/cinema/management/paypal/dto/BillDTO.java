package com.example.cinema.management.paypal.dto;

import com.example.cinema.management.model.Bill;
import com.example.cinema.management.paypal.config.MoneyConfig;
import com.example.cinema.management.paypal.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillDTO {
    @JsonProperty(value = "purchase_units")
    private List<PurchaseUnit> purchaseUnits;

    @JsonProperty(value = "intent")
    private Intent intent;

    @JsonProperty(value = "application_context")
    private PayPalApplicationContext applicationContext;


    public static BillDTO toBillDTO(Bill bill, MoneyConfig moneyConfig){
        PurchaseUnit purchaseUnit = PurchaseUnit.builder()
                .items(Item.toListItem(bill.getTicketList(), bill.getBuyProducts(), moneyConfig))
                .referenceId(bill.getVerification_code())
                .build();
        Amount amount = Amount.builder()
                .currencyCode(moneyConfig.getCurrency())
                .value(CurencyConverter.vndToUSD(bill.getTotal(), moneyConfig))
                .breakDown(BreakDown.builder()
                        .itemTotal(MoneyDTO.builder()
                                .currencyCode(moneyConfig.getCurrency())
                                .value(purchaseUnit.totalUnitMoney())
                                .build())
                        .taxTotal(MoneyDTO.builder()
                                .currencyCode(moneyConfig.getCurrency())
                                .value(purchaseUnit.totalTaxMoney())
                                .build())
                        .build())
                .build();
        purchaseUnit.setAmount(amount);
        List<PurchaseUnit> purchaseUnitList = new ArrayList<>();
        purchaseUnitList.add(purchaseUnit);
        return BillDTO.builder()
                .purchaseUnits(purchaseUnitList)
                .intent(Intent.CAPTURE)
                .build();
    }
}

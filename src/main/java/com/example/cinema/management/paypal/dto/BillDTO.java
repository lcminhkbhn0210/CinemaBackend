package com.example.cinema.management.paypal.dto;

import com.example.cinema.management.model.Bill;
import com.example.cinema.management.paypal.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static BillDTO toBillDTO(Bill bill){
        Amount amount = Amount.builder()
                .currencyCode("USD")
                .value(String.valueOf(bill.getTotal()))
                .breakDown(BreakDown.builder()
                        .itemTotal(MoneyDTO.builder()
                                .currencyCode("USD")
                                .value(String.valueOf(bill.getTotal()*0.95*0.05))
                                .build())
                        .taxTotal(MoneyDTO.builder()
                                .currencyCode("USD")
                                .value(String.valueOf(bill.getTotal()*0.05*0.05))
                                .build())
                        .build())
                .build();
        PurchaseUnit purchaseUnit = PurchaseUnit.builder()
                .amount(amount)
                .items(Item.toListItem(bill.getTicketList(), bill.getBuyProducts()))
                .referenceId(bill.getVerification_code())
                .build();
        List<PurchaseUnit> purchaseUnitList = new ArrayList<>();
        purchaseUnitList.add(purchaseUnit);
        return BillDTO.builder()
                .purchaseUnits(purchaseUnitList)
                .intent(Intent.CAPTURE)
                .build();
    }
}

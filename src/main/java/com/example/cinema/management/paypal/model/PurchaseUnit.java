package com.example.cinema.management.paypal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseUnit {

    @JsonProperty(value = "reference_id")
    private String referenceId;

    @JsonProperty(value = "amount")
    private Amount amount;

    @JsonProperty(value = "items")
    private List<Item> items;

    @JsonProperty(value = "payee")
    private Payee payee;

    public String totalUnitMoney(){
        BigDecimal bigDecimal = new BigDecimal("0");
        for(Item item:items){
            BigDecimal bigDecimal1 = new BigDecimal(item.getUnitMoneyDTO().getValue());
            bigDecimal1 = bigDecimal1.multiply(new BigDecimal(item.getQuantity()));
            bigDecimal = bigDecimal.add(bigDecimal1);
        }
        return bigDecimal.stripTrailingZeros().toString();
    }

    public String totalTaxMoney(){
        BigDecimal bigDecimal = new BigDecimal("0");
        for(Item item:items){
            BigDecimal bigDecimal1 = new BigDecimal(item.getTax().getValue());
            bigDecimal1 = bigDecimal1.multiply(new BigDecimal(item.getQuantity()));
            bigDecimal = bigDecimal.add(bigDecimal1);
        }
        return bigDecimal.stripTrailingZeros().toString();
    }
}

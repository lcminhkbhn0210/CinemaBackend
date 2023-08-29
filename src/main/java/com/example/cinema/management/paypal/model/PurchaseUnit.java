package com.example.cinema.management.paypal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public double totalUnitMoney(){
        double sum = 0;
        for(int i=0; i<items.size();i++){
           sum = sum +  Double.parseDouble(items.get(i).getUnitMoneyDTO().getValue());
        }
        return sum;
    }

    public  double totalTaxMoney(){
        double sum = 0;
        for(int i=0; i<items.size();i++){
            sum = sum +  Double.parseDouble(items.get(i).getTax().getValue());
        }
        return sum;
    }
}

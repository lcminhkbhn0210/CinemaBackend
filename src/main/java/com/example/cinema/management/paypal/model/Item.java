package com.example.cinema.management.paypal.model;

import com.example.cinema.management.model.BuyProduct;
import com.example.cinema.management.model.Ticket;
import com.example.cinema.management.paypal.config.MoneyConfig;
import com.example.cinema.management.paypal.dto.CurencyConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String name;

    @JsonProperty(value = "description")
    private String description;
    private String quantity;

    @JsonProperty(value = "unit_amount")
    private MoneyDTO unitMoneyDTO;
    private MoneyDTO tax;


    public static List<Item> toListItem(List<Ticket> tickets, List<BuyProduct> buyProducts, MoneyConfig moneyConfig){
        List<Item> items = new ArrayList<>();
        for(Ticket ticket:tickets){
            Item item = Item.builder()
                    .name(ticket.getFilmRoomChair().getName())
                    .description(ticket.getDes())
                    .quantity("1")
                    .unitMoneyDTO(MoneyDTO.builder()
                            .currencyCode(moneyConfig.getCurrency())
                            .value(CurencyConverter.vndToUSD(ticket.getPrice() * (1-moneyConfig.getFee()),moneyConfig))
                            .build())
                    .tax(MoneyDTO.builder()
                            .currencyCode(moneyConfig.getCurrency())
                            .value(CurencyConverter.vndToUSD(ticket.getPrice() * moneyConfig.getFee(), moneyConfig))
                            .build())
                    .build();
            items.add(item);
        }
        for(BuyProduct buyProduct:buyProducts){
            Item item = Item.builder()
                    .name(buyProduct.getSellProduct().getName())
                    .description(buyProduct.getSellProduct().getDes())
                    .quantity(String.valueOf(buyProduct.getAmount()))
                    .unitMoneyDTO(MoneyDTO.builder()
                            .currencyCode(moneyConfig.getCurrency())
                            .value(new BigDecimal(CurencyConverter.vndToUSD(buyProduct.getSellProduct().getPrice() * (1-moneyConfig.getFee()),moneyConfig)).setScale(2, RoundingMode.CEILING).toString())
                            .build())
                    .tax(MoneyDTO.builder()
                            .currencyCode(moneyConfig.getCurrency())
                            .value(new BigDecimal(CurencyConverter.vndToUSD(buyProduct.getSellProduct().getPrice() * moneyConfig.getFee(),moneyConfig)).setScale(2,RoundingMode.DOWN).toString())
                            .build())
                    .build();
            items.add(item);
        }
        return items;
    }
}

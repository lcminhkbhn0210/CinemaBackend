package com.example.cinema.management.paypal.model;

import com.example.cinema.management.model.BuyProduct;
import com.example.cinema.management.model.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static List<Item> toListItem(List<Ticket> tickets, List<BuyProduct> buyProducts){
        List<Item> items = new ArrayList<>();
        for(Ticket ticket:tickets){
            Item item = Item.builder()
                    .name(ticket.getFilmRoomChair().getName())
                    .description(ticket.getDes())
                    .quantity("1")
                    .unitMoneyDTO(MoneyDTO.builder()
                            .currencyCode("USD")
                            .value(String.valueOf(ticket.getPrice()*0.95*0.05))
                            .build())
                    .tax(MoneyDTO.builder()
                            .currencyCode("USD")
                            .value(String.valueOf(ticket.getPrice()*0.05*0.05))
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
                            .currencyCode("USD")
                            .value(String.valueOf(buyProduct.getSellProduct().getPrice()*0.95*0.05))
                            .build())
                    .tax(MoneyDTO.builder()
                            .currencyCode("USD")
                            .value(String.valueOf(buyProduct.getSellProduct().getPrice()*0.05*0.05))
                            .build())
                    .build();
            items.add(item);
        }
        return items;
    }
}

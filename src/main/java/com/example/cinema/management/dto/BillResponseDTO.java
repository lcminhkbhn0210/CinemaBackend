package com.example.cinema.management.dto;

import com.example.cinema.management.model.Bill;
import com.example.cinema.management.model.BuyProduct;
import com.example.cinema.management.model.Message;
import com.example.cinema.management.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillResponseDTO extends Message {
    private double total;
    private Date sell_date;
    private String code;
    private List<Ticket> tickets;
    private List<BuyProduct> products;

    public BillResponseDTO (String message, String status){
        super(status, message);
    }

    public static BillResponseDTO toBillResponseDTO(Bill bill){
        return BillResponseDTO.builder()
                .code(bill.getVerification_code())
                .products(bill.getBuyProducts())
                .sell_date(bill.getSellDate())
                .tickets(bill.getTicketList())
                .total(bill.getTotal())
                .build();
    }
}

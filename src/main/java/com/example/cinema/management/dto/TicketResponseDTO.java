package com.example.cinema.management.dto;

import com.example.cinema.management.model.Message;
import com.example.cinema.management.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO extends Message {
    private long id;
    private String des;
    private double price;
    private boolean sold;
    private String name;
    public static TicketResponseDTO toTicketResponseDTO(Ticket ticket){
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        ticketResponseDTO.setDes(ticket.getDes());
        ticketResponseDTO.setId(ticket.getId());
        ticketResponseDTO.setName(ticket.getFilmRoomChair().getName());
        ticketResponseDTO.setSold(ticket.isSold());
        ticketResponseDTO.setPrice(ticket.getPrice());
        return ticketResponseDTO;
    }

    public TicketResponseDTO(String message, String status,Ticket ticket){
        super(status,message);
        this.id = ticket.getId();
        this.des = ticket.getDes();
        this.price = ticket.getPrice();
        this.sold = ticket.isSold();
        this.name = ticket.getFilmRoomChair().getName();
    }
}

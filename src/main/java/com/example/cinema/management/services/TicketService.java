package com.example.cinema.management.services;

import com.example.cinema.management.dto.TicketResponseDTO;
import com.example.cinema.management.model.ShowTimes;
import com.example.cinema.management.model.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> createTicketByShowTimes(ShowTimes showTimes);
    List<TicketResponseDTO> buyTickets(List<Ticket> tickets) throws InterruptedException;
    List<TicketResponseDTO> getListTicketByShowTimesId(long id);
    Ticket updateTicket( long id);
    void blockTicket(List<Ticket> tickets);
    void unBlockTicket(List<Ticket> tickets) throws InterruptedException;

}

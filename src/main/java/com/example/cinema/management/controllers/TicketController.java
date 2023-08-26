package com.example.cinema.management.controllers;

import com.example.cinema.management.dto.TicketResponseDTO;
import com.example.cinema.management.model.Ticket;
import com.example.cinema.management.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("{id}")
    public List<TicketResponseDTO> getListTicketByShowTimesId(@PathVariable("id") long id){
        return ticketService.getListTicketByShowTimesId(id);
    }

    @GetMapping("/buyTicket")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponseDTO> buyTicket(@RequestBody List<Ticket> tickets) throws InterruptedException {
        return ticketService.buyTickets(tickets);
    }
}

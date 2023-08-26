package com.example.cinema.management.services.impl;

import com.example.cinema.management.dto.TicketResponseDTO;
import com.example.cinema.management.model.FilmRoomChair;
import com.example.cinema.management.model.ShowTimes;
import com.example.cinema.management.model.Ticket;
import com.example.cinema.management.model.TypeRoomChair;
import com.example.cinema.management.repositories.TicketRepository;
import com.example.cinema.management.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TicketServiceImp implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public List<Ticket> createTicketByShowTimes(ShowTimes showTimes) {
        List<Ticket> tickets = new ArrayList<>();
        for(FilmRoomChair filmRoomChair:showTimes.getFilmRoom().getFilmRoomChairList()){
            double price = 60000;
            String des = "Ve thuong";
            if(filmRoomChair.getType().equals(TypeRoomChair.VIP)) {
                price = 75000;
                des = "Ve Vip";
            }
            Ticket ticket = Ticket.builder()
                    .showTimes(showTimes)
                    .des(des)
                    .sold(false)
                    .price(price)
                    .filmRoomChair(filmRoomChair)
                    .build();
            ticketRepository.save(ticket);
            tickets.add(ticket);
        }
        return tickets;
    }

    @Override
    public List<TicketResponseDTO> buyTickets(List<Ticket> tickets) throws InterruptedException {
        boolean check = true;
        List<TicketResponseDTO> ticketResponseDTOS = new ArrayList<>();
        for(Ticket ticket:tickets){
            Ticket t = ticketRepository.findById(ticket.getId()).get();
            if(t.getStatus() == 1 || t.isSold()) {
                check = false;
                ticketResponseDTOS.add(new TicketResponseDTO("Ghe dang duoc dat boi nguoi khac.","200: OK",t));
            }else ticketResponseDTOS.add(new TicketResponseDTO("Ghe co the dat","200: OK",t));
        }
        if(check == true){
            blockTicket(tickets);
            CompletableFuture.runAsync(() -> {
                try {
                    unBlockTicket(tickets);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            return ticketResponseDTOS;
        }
        return ticketResponseDTOS;
    }

    @Override
    public List<TicketResponseDTO> getListTicketByShowTimesId(long id) {
        List<TicketResponseDTO> list = new ArrayList<>();
        List<Ticket> tickets = ticketRepository.getTicketsByShowTimesId(id);
        for (Ticket ticket:tickets){
            list.add(TicketResponseDTO.toTicketResponseDTO(ticket));
        }
        return list;
    }

    @Override
    public Ticket updateTicket(long id) {
        Optional<Ticket> ticketDb = ticketRepository.findById(id);
        if(ticketDb.isPresent()){
            Ticket ticket = ticketDb.get();
            ticket.setSold(true);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    @Override
    public void blockTicket(List<Ticket> tickets) {
        for (Ticket ticket:tickets){
            ticketRepository.updateStatus(ticket.getId());
        }
    }

    @Async
    @Override
    public void unBlockTicket(List<Ticket> tickets) throws InterruptedException {
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName());
        for (Ticket ticket:tickets){
            ticketRepository.updateUnStatus(ticket.getId());
        }
    }

}

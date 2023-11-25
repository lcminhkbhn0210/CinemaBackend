package com.example.cinema.management.services.impl;

import com.example.cinema.management.model.Cinema;
import com.example.cinema.management.repositories.CinemaRepository;
import com.example.cinema.management.services.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CinemaServiceImp implements CinemaService {
    private CinemaRepository cinemaRepository;
    @Override
    public List<Cinema> getAllCinema() {
        return cinemaRepository.findAll();
    }
}

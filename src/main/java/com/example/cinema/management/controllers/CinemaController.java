package com.example.cinema.management.controllers;

import com.example.cinema.management.model.Cinema;
import com.example.cinema.management.services.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@AllArgsConstructor
public class CinemaController {
    private CinemaService cinemaService;

    @GetMapping("")
    public ResponseEntity<List<Cinema>> getAllCinema(){
        return new ResponseEntity<>(cinemaService.getAllCinema(), HttpStatus.OK);
    }
}

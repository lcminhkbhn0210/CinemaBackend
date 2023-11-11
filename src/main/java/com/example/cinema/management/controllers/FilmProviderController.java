package com.example.cinema.management.controllers;

import com.example.cinema.management.model.FilmProvider;
import com.example.cinema.management.services.FilmProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filmProvider")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class FilmProviderController {

    private final FilmProviderService filmProviderService;

    @GetMapping()
    public ResponseEntity<List<FilmProvider>> getAllFilmProvider(){
        return new ResponseEntity<>(filmProviderService.getAllFilmProvider(), HttpStatus.OK);
    }
}

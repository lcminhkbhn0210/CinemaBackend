package com.example.cinema.management.controllers;

import com.example.cinema.management.model.FilmRoom;
import com.example.cinema.management.services.FilmRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filmroom")
public class FilmRoomController {

    @Autowired
    private FilmRoomService filmRoomService;

    @PostMapping
    public ResponseEntity<FilmRoom> createFilmRoom(@RequestBody FilmRoom filmRoom){
        return new ResponseEntity<>(filmRoomService.createRoom(filmRoom), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<FilmRoom> getRoomById(@PathVariable("id") long id){
        return new ResponseEntity<>(filmRoomService.getRoomById(id),HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<FilmRoom> getRoomByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(filmRoomService.getRoomByName(name), HttpStatus.OK);
    }

}

package com.example.cinema.management.controllers;

import com.example.cinema.management.model.Film;
import com.example.cinema.management.model.ShowTimes;
import com.example.cinema.management.services.FilmService;
import com.example.cinema.management.services.ShowTimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private ShowTimesService showTimesService;

    @PostMapping("")
    public ResponseEntity<Film> addFilm(@RequestBody Film film){
        return new ResponseEntity<>(filmService.saveFilm(film), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film, @PathVariable("id") long id){
        return new ResponseEntity<>(filmService.updateFilm(film,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable("id") long id){
        filmService.deleteFilm(id);
        return new ResponseEntity<String>("Da xoa thanh cong",HttpStatus.OK);
    }

    @GetMapping("/by-name")
    public List<Film> getListFilmByName(@RequestParam("name") String name){
        return filmService.getListFilmByName(name);
    }

    @GetMapping
    public List<Film> getAllFilm(){
        return filmService.getAllFilm();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable("id") long id){
        return new ResponseEntity<>(filmService.getFilmById(id),HttpStatus.OK);
    }

    @GetMapping("/showtimes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ShowTimes> getShowTimesByFilmId(@PathVariable("id") long id){
        return showTimesService.getListShowTimesByFilmId(id,new Date());
    }

}

package com.example.cinema.management.controllers;

import com.example.cinema.management.dto.ShowTimesRequestDTO;
import com.example.cinema.management.dto.ShowTimesResponseDTO;
import com.example.cinema.management.model.ShowTimes;
import com.example.cinema.management.services.ShowTimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/showtimes")
public class ShowTimesController {
    @Autowired
    private ShowTimesService showTimesService;

    @GetMapping("{id}")
    public ResponseEntity<ShowTimes> getShowTimesById(@PathVariable("id") long id){
        return new ResponseEntity<>(showTimesService.getShowTimesById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ShowTimesResponseDTO> createShowTimes(@RequestBody ShowTimesRequestDTO showTimesRequestDTO) throws ParseException {
        return new ResponseEntity<>(showTimesService.createShowTimes(showTimesRequestDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteShowTimes(@PathVariable("id") long id){
        showTimesService.deleteShowTimes(id);
        return new ResponseEntity<>("Xoa Thanh Cong",HttpStatus.OK);
    }
    @GetMapping("/day")
    public List<ShowTimes> getShowTimesByDay(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date date) throws ParseException {
        return showTimesService.getShowTimesByDay(date);
    }
    @GetMapping
    public ResponseEntity<List<ShowTimes>> getAllShowTimes(){
        return new ResponseEntity<>(showTimesService.getAllShowTimes(), HttpStatus.OK);
    }
}

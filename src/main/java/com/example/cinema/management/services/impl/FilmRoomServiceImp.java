package com.example.cinema.management.services.impl;

import com.example.cinema.management.exceptions.ResourceNotFoundException;
import com.example.cinema.management.model.FilmRoom;
import com.example.cinema.management.repositories.FilmRoomRepository;
import com.example.cinema.management.services.FilmRoomChairService;
import com.example.cinema.management.services.FilmRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FilmRoomServiceImp implements FilmRoomService {

    @Autowired
    private FilmRoomRepository filmRoomRepository;
    @Autowired
    private FilmRoomChairService filmRoomChairService;
    @Override
    public FilmRoom createRoom(FilmRoom filmRoom) {
        if (filmRoomRepository.findByName(filmRoom.getName()).isPresent()){
            return FilmRoom.builder()
                    .note("Phong da ton tai")
                    .name(filmRoom.getName())
                    .build();
        }else {
            FilmRoom filmRoomDb = filmRoomRepository.saveAndFlush(filmRoom);
            filmRoomChairService.createFilmRoomChairByRoom(filmRoomDb);
            return filmRoomDb;
        }
    }

    @Override
    public void deleteRoom(long id) {
        FilmRoom filmRoomDb = filmRoomRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("FilmRoom","ID",id)
        );
        filmRoomRepository.delete(filmRoomDb);
    }

    @Override
    public FilmRoom getRoomById(long id) {
        Optional<FilmRoom> filmRoomDb = filmRoomRepository.findById(id);
        if(filmRoomDb.isPresent())
            return filmRoomDb.get();
        return null;
    }

    @Override
    public FilmRoom getRoomByName(String name) {
        Optional<FilmRoom> filmRoomDb = filmRoomRepository.findByName(name);
        if(filmRoomDb.isPresent())
            return filmRoomDb.get();
        return null;
    }
}

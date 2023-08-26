package com.example.cinema.management.services;

import com.example.cinema.management.model.FilmRoom;
import com.example.cinema.management.model.FilmRoomChair;

import java.util.List;

public interface FilmRoomChairService {
    FilmRoomChair saveFilmRoomChair(FilmRoomChair filmRoomChair);
    List<FilmRoomChair> createFilmRoomChairByRoom(FilmRoom filmRoom);
}

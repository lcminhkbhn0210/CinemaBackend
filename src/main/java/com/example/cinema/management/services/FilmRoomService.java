package com.example.cinema.management.services;

import com.example.cinema.management.model.FilmRoom;

public interface FilmRoomService {
    FilmRoom createRoom(FilmRoom filmRoom);

    void deleteRoom(long id);

    FilmRoom getRoomById(long id);

    FilmRoom getRoomByName(String name);
}

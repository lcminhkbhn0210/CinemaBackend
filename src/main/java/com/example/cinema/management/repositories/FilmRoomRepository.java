package com.example.cinema.management.repositories;

import com.example.cinema.management.model.FilmRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRoomRepository extends JpaRepository<FilmRoom,Long> {

    Optional<FilmRoom> findByName(String name);
}

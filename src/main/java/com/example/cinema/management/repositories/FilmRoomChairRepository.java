package com.example.cinema.management.repositories;

import com.example.cinema.management.model.FilmRoomChair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRoomChairRepository extends JpaRepository<FilmRoomChair, Long> {
}

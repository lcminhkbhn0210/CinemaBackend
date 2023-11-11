package com.example.cinema.management.repositories;

import com.example.cinema.management.model.FilmProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmProviderRepository extends JpaRepository<FilmProvider, Long> {
}

package com.example.cinema.management.services;

import com.example.cinema.management.model.Film;

import java.util.List;

public interface FilmService {
    Film saveFilm(Film film);
    void deleteFilm(long id);
    Film updateFilm(Film film, long id);
    List<Film> getAllFilm();
    Film getFilmById(long id);
    List<Film> getListFilmByName(String name);
}

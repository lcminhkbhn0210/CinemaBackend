package com.example.cinema.management.services.impl;

import com.example.cinema.management.exceptions.ResourceNotFoundException;
import com.example.cinema.management.model.Film;
import com.example.cinema.management.repositories.FilmRepository;
import com.example.cinema.management.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImp implements FilmService {

    @Autowired
    private FilmRepository filmRepository;
    @Override
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public void deleteFilm(long id) {
        filmRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Film","ID",id)
        );
        filmRepository.deleteById(id);
    }

    @Override
    public Film updateFilm(Film film, long id) {
        Film filmExist = filmRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Film","ID",id)
        );
        filmExist.setName(film.getName());
        filmExist.setDes(film.getDes());
        filmExist.setImg(film.getImg());
        filmExist.setLength(film.getLength());
        filmExist.setRate(film.getRate());
        filmExist.setDirectory(film.getDirectory());
        filmExist.setCreated(film.getCreated());
        filmExist.setFilmProvider(film.getFilmProvider());
        filmExist.setFilmRating(film.getFilmRating());
        return filmRepository.save(filmExist);
    }

    @Override
    public List<Film> getAllFilm() {
        return filmRepository.findAll();
    }

    @Override
    public Film getFilmById(long id) {
        Optional<Film> filmdb = filmRepository.findById(id);
        if(filmdb.isPresent()){
            return filmdb.get();
        }
        return null;
    }

    @Override
    public List<Film> getListFilmByName(String name) {
        List<Film> films = filmRepository.findByNameIsLike(name);
        for (Film film:films){
            film.setShowtimes(null);
        }
        return films;
    }
}

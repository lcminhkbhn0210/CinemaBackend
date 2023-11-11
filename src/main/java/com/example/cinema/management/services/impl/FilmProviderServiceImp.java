package com.example.cinema.management.services.impl;

import com.example.cinema.management.model.FilmProvider;
import com.example.cinema.management.repositories.FilmProviderRepository;
import com.example.cinema.management.services.FilmProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmProviderServiceImp implements FilmProviderService {
    private final FilmProviderRepository filmProviderRepository;
    @Override
    public List<FilmProvider> getAllFilmProvider() {
        return filmProviderRepository.findAll();
    }
}

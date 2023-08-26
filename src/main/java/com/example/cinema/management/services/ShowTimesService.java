package com.example.cinema.management.services;

import com.example.cinema.management.dto.ShowTimesRequestDTO;
import com.example.cinema.management.dto.ShowTimesResponseDTO;
import com.example.cinema.management.model.ShowTimes;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ShowTimesService {
    ShowTimesResponseDTO createShowTimes(ShowTimesRequestDTO showTimesRequestDTO) throws ParseException;
    void deleteShowTimes(long id);
    String updateShowTimes(ShowTimes showTimes, long id);
    List<ShowTimes> getShowTimesByDay(Date date) throws ParseException;
    ShowTimes getShowTimesById(long id);
    List<ShowTimes> getListShowTimesByFilmId(long id, Date date);

}

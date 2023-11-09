package com.example.cinema.management.services.impl;

import com.example.cinema.management.dto.ShowTimesRequestDTO;
import com.example.cinema.management.dto.ShowTimesResponseDTO;
import com.example.cinema.management.model.FilmRoom;
import com.example.cinema.management.model.ShowTimes;
import com.example.cinema.management.repositories.FilmRepository;
import com.example.cinema.management.repositories.FilmRoomRepository;
import com.example.cinema.management.repositories.ShowTimesRepository;
import com.example.cinema.management.services.ShowTimesService;
import com.example.cinema.management.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowTimesServiceImp implements ShowTimesService {


    private final ShowTimesRepository showTimesRepository;

    private final FilmRepository filmRepository;

    private final FilmRoomRepository filmRoomRepository;

    private final TicketService ticketService;
    @Override
    public ShowTimesResponseDTO createShowTimes(ShowTimesRequestDTO showTimesRequestDTO) throws ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        String dateendString = "";
        dateendString = dateString.substring(0,10);
        dateendString = dateendString +" "+ "23:59:59";
        ShowTimes showTimes = showTimesRequestDTO.getShowTimes();
        showTimes.setFilm(filmRepository.findById(showTimesRequestDTO.getFilm_id()).get());
        showTimes.setFilmRoom(filmRoomRepository.findById(showTimesRequestDTO.getRoom_id()).get());
        List<ShowTimes> showTimesList = showTimesRepository.getShowTimesByDateAndFilmRoom(date,dateFormat.parse(dateendString),showTimes.getFilmRoom().getId());
        date.setTime(showTimes.getTimestart().getTime() + showTimes.getFilm().getLength()*60*1000);
        showTimes.setTimeend(date);
        boolean check = true;
        for(ShowTimes s:showTimesList){
            int start_start = s.getTimestart().compareTo(showTimes.getTimestart());
            int end_end = s.getTimeend().compareTo(showTimes.getTimeend());
            int start_end = s.getTimestart().compareTo(showTimes.getTimeend());
            int end_start = s.getTimeend().compareTo(showTimes.getTimestart());
            if(
                       (start_start<=0 && end_end>=0 && start_end<=0 && end_start>=0)
                    || (start_start>=0 && end_end>=0 && start_end<=0 && end_start>=0)
                    || (start_start<=0 && end_end<=0 && start_end<=0 && end_start>=0)
                    || (start_start>=0 && end_end<=0 && start_end<=0 && end_start>=0)
            )check = false;
        }

        if(showTimes.getTimestart().compareTo(new Date())<0) check = false;
        if (check) {
            ShowTimesResponseDTO showTimesResponseDTO = new ShowTimesResponseDTO();
            ShowTimes showTimesDb = showTimesRepository.save(showTimes);
            ticketService.createTicketByShowTimes(showTimesDb);
            showTimesResponseDTO.setShowTimes(showTimesDb);
            showTimesResponseDTO.setStatus("202 Create");
            showTimesResponseDTO.setMessage("Tao Phong Thanh Cong");
            return showTimesResponseDTO;
        }
        return new ShowTimesResponseDTO("Tao Phong That Bai. Khung gio do da co lich chieu","401: Not Create", null);
    }

    @Override
    public void deleteShowTimes(long id) {
        Optional<ShowTimes> showTimes = showTimesRepository.findById(id);
        if(showTimes.isPresent()) showTimesRepository.deleteById(id);
    }

    @Override
    public String updateShowTimes(ShowTimes showTimes, long id) {
        Optional<ShowTimes> showTimesDb = showTimesRepository.findById(id);
        if(showTimesDb.isPresent()){
            ShowTimes showTimesUpdate = new ShowTimes();
            showTimesUpdate.setId(showTimes.getId());
            showTimesUpdate.setTimeend(showTimes.getTimeend());
            showTimesUpdate.setTimestart(showTimes.getTimestart());
            showTimesUpdate.setFilm(showTimes.getFilm());
            showTimesUpdate.setFilmRoom(showTimes.getFilmRoom());
            showTimesUpdate.setTicketList(showTimes.getTicketList());
            showTimesRepository.save(showTimesUpdate);
            return "Cap nhat thanh cong";
        }
        return "Cap nhat that bai, id khong ton tai";
    }

    @Override
    public List<ShowTimes> getShowTimesByDay(Date date) throws ParseException {
        String dateendString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        dateendString = dateString.substring(0,10);
        dateendString = dateendString +" "+ "23:59:59";
        return showTimesRepository.getShowTimesByDate(date,dateFormat.parse(dateendString));
    }

    @Override
    public ShowTimes getShowTimesById(long id) {
        Optional<ShowTimes> showTimes = showTimesRepository.findById(id);
        if(showTimes.isPresent()) return showTimes.get();
        return null;
    }

    @Override
    public List<ShowTimes> getListShowTimesByFilmId(long id, Date date) {
        List<ShowTimes> showTimes = showTimesRepository.getListShowTimesByFilmId(id,new Date());
        for (ShowTimes show:showTimes){
            show.setTicketList(null);
            FilmRoom filmRoom = show.getFilmRoom();
            filmRoom.setFilmRoomChairList(null);
            show.setFilmRoom(filmRoom);
        }
        return showTimes;
    }
}

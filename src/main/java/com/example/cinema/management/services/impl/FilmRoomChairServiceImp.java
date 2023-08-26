package com.example.cinema.management.services.impl;

import com.example.cinema.management.model.FilmRoom;
import com.example.cinema.management.model.FilmRoomChair;
import com.example.cinema.management.model.TypeRoomChair;
import com.example.cinema.management.repositories.FilmRoomChairRepository;
import com.example.cinema.management.services.FilmRoomChairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmRoomChairServiceImp implements FilmRoomChairService {

    @Autowired
    private FilmRoomChairRepository filmRoomChairRepository;
    @Override
    public FilmRoomChair saveFilmRoomChair(FilmRoomChair filmRoomChair) {
        return filmRoomChairRepository.save(filmRoomChair);
    }

    @Override
    public List<FilmRoomChair> createFilmRoomChairByRoom(FilmRoom filmRoom) {
        List<FilmRoomChair> filmRoomChairs = new ArrayList<>();
        for(int i=1; i<=8; i++){
            String s = "A";
            TypeRoomChair type = TypeRoomChair.VIP;
            for(int j=1; j<=8;j++){
                s = choseCharacter(i);
                if(i==1 || i==2) type = TypeRoomChair.NORMAL;
                FilmRoomChair filmRoomChair = FilmRoomChair.builder()
                        .name(filmRoom.getName()+"-"+s+j)
                        .type(type)
                        .filmRoom(filmRoom)
                        .build();
                filmRoomChairs.add(filmRoomChair);
            }
        }
        return filmRoomChairRepository.saveAll(filmRoomChairs);
    }

    private String choseCharacter(int number){
        switch (number) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            case 8:
                return "H";
            default:
                return "A";
        }
    }
}

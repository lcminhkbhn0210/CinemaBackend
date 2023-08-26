package com.example.cinema.management.dto;

import com.example.cinema.management.model.ShowTimes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimesRequestDTO {
    private long room_id;
    private long film_id;
    private ShowTimes showTimes;
}

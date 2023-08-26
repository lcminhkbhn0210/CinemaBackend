package com.example.cinema.management.dto;

import com.example.cinema.management.model.Message;
import com.example.cinema.management.model.ShowTimes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowTimesResponseDTO extends Message {
    private ShowTimes showTimes;
    public ShowTimesResponseDTO(String message, String status, ShowTimes showTimes){
        super(message,status);
        this.showTimes = showTimes;
    }
}

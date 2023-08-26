package com.example.cinema.management.dto;

import com.example.cinema.management.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO extends Message {
    private String name;
    private String type;
    private String img;

    public ProductResponseDTO(String message,String status){
        super(message,status);
    }
}

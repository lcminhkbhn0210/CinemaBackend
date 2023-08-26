package com.example.cinema.management.dto;

import com.example.cinema.management.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterResponseDTO {
    private String message;
    private User user;
}

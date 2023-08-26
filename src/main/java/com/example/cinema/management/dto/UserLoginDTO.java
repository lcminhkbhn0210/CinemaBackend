package com.example.cinema.management.dto;

import com.example.cinema.management.model.Role;
import com.example.cinema.management.model.Type;
import com.example.cinema.management.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDTO {
    private String username;
    private Type type;
    private Set<Role> authorities;
    private String email;

    public static UserLoginDTO toUserDTO(User user){
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .username(user.getUsername())
                .type(user.getType())
                .authorities((Set<Role>) user.getAuthorities())
                .email(user.getEmail())
                .build();
        return userLoginDTO;
    }
}

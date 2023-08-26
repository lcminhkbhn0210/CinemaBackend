package com.example.cinema.management.dto;

public class LoginResponseDTO {

    private String jwt;
    private String message;
    private UserLoginDTO userLoginDTO;
    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(UserLoginDTO userLoginDTO, String jwt, String message) {
        this.userLoginDTO = userLoginDTO;
        this.jwt = jwt;
        this.message = message;
    }

    public UserLoginDTO getUserDTO() {
        return userLoginDTO;
    }

    public void setUserDTO(UserLoginDTO userLoginDTO) {
        this.userLoginDTO = userLoginDTO;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.example.cinema.management.paypal.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaypalResponse {
    private String code;
    private String filmName;
    private String cinema;
    private Date dateStart;
    private Date dateEnd;
}

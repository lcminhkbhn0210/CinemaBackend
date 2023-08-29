package com.example.cinema.management.paypal.dto;


import com.example.cinema.management.paypal.config.MoneyConfig;

import java.math.BigDecimal;

public class CurencyConverter {
    public static String vndToUSD(double vndAmount, MoneyConfig moneyConfig){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(vndAmount));
        return bigDecimal.multiply(new BigDecimal(moneyConfig.getTransToUSD())).stripTrailingZeros().toString();
    }
}

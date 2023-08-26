package com.example.cinema.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillRequestDTO {
    private List<Long> listTicketId;
    private List<Long> listBuyProductId;
    private Long employee_id;
    private Long customer_id;

}

package com.example.cinema.management.paypal.dto;

import com.example.cinema.management.paypal.model.Payer;
import com.example.cinema.management.paypal.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillResponsePayPalDTO {
    @JsonProperty(value = "create_time")
    private String createTime;

    @JsonProperty(value = "update_time")
    private String updateTime;

    @JsonProperty(value = "id")
    private String id;

    private List<LinkDTO> links;
    private Payer payer;
    private Status status;
}

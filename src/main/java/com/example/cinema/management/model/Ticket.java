package com.example.cinema.management.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String des;
    private double price;
    private boolean sold;
    private long status;

    @ManyToOne()
    @JoinColumn(name = "showtimes_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private ShowTimes showTimes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "filmRoomChair_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private FilmRoomChair filmRoomChair;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Bill bill;
}

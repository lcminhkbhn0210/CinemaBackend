package com.example.cinema.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",unique = true)
    private String name;
    private String des;
    @JsonIgnore
    private int amount;
    @Column(length = 2000)
    private String img;

    @ManyToOne()
    @JoinColumn(name = "cinemaId")
    private Cinema cinema;
}

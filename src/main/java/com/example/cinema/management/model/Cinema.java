package com.example.cinema.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinema")
    @JsonIgnore
    private List<User> users;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinema")
    @JsonIgnore
    private List<FilmRoom> filmRooms;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinema")
    @JsonIgnore
    private List<Product> products;
}

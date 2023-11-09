package com.example.cinema.management.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_filmprovider")
@Transactional
public class FilmProvider extends Provider {
    private String des;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filmProvider", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Film> films;

}

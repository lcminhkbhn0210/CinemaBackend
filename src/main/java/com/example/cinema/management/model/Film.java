package com.example.cinema.management.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "tbl_film")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "des")
    private String des;

    @Column(name = "rate")
    private int rate;

    @Column(name = "directory")
    private String directory;

    @Column(name = "length")
    private long length;

    @Column(name = "link_img", length = 2000)
    private String img;

    private Date created;
    @Enumerated(EnumType.STRING)
    private FilmRating filmRating;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ShowTimes> showtimes;

}

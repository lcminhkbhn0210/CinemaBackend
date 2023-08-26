package com.example.cinema.management.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_showtimes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ShowTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "time_start")
    private Date timestart;

    @Column(name = "time_end")
    private Date timeend;

    @ManyToOne()
    @JoinColumn(name = "film_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Film film;

    @ManyToOne()
    @JoinColumn(name = "filmroom_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private FilmRoom filmRoom;

    @OneToMany(mappedBy = "showTimes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Ticket> ticketList;
}

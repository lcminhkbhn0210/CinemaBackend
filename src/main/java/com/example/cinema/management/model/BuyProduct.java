package com.example.cinema.management.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_buyproduct")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BuyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int amount;

    @ManyToOne()
    @JoinColumn(name = "sellProduct_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SellProduct sellProduct;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Bill bill;
}

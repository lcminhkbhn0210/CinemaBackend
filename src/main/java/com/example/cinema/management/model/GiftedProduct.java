package com.example.cinema.management.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_giftedproduct")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class GiftedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String note;
    private int amount;

    @ManyToOne()
    @JoinColumn(name = "giftProduct_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private GiftProduct giftProduct;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Bill bill;
}

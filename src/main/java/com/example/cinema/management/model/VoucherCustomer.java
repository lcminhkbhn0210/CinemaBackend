package com.example.cinema.management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class VoucherCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "voucherId")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
}

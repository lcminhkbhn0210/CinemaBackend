package com.example.cinema.management.repositories;

import com.example.cinema.management.model.VoucherCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherCustomerRepository extends JpaRepository<VoucherCustomer, Long> {
}

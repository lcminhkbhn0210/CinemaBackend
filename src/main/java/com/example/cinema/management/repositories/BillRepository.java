package com.example.cinema.management.repositories;

import com.example.cinema.management.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    @Query(value = "SELECT * FROM tbl_bill WHERE code = :code AND expire >= :current_date", nativeQuery = true)
    Bill findByVerification_code(@Param("code") String code, @Param("current_date") Date date);

    Bill findByPaypalOrderId(String id);

}

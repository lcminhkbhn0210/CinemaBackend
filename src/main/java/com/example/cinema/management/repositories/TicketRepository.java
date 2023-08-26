package com.example.cinema.management.repositories;

import com.example.cinema.management.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query(value = "update tbl_ticket SET status = 1 WHERE id = :id",nativeQuery = true)
    @Modifying
    @Transactional
    void updateStatus(@Param("id") long id);
    @Query(value = "update tbl_ticket SET status = 0 WHERE id = :id",nativeQuery = true)
    @Modifying
    @Transactional
    void updateUnStatus(@Param("id") long id);
    List<Ticket> getTicketsByShowTimesId(@Param("id") long id);
}

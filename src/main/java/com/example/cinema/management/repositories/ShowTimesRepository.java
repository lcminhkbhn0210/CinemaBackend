package com.example.cinema.management.repositories;

import com.example.cinema.management.model.ShowTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShowTimesRepository extends JpaRepository<ShowTimes,Long> {

    @Query(value = "SELECT * FROM tbl_showtimes WHERE time_start>= :date_start AND time_end<= :date_end", nativeQuery = true)
    List<ShowTimes> getShowTimesByDate(@Param("date_start") Date date_start, @Param("date_end") Date date_end);

    @Query(value = "SELECT * FROM tbl_showtimes WHERE time_end>= :date_start AND time_end<= :date_end AND filmroom_id = :filmroom_id ORDER BY time_start ASC", nativeQuery = true)
    List<ShowTimes> getShowTimesByDateAndFilmRoom(@Param("date_start") Date date_start, @Param("date_end") Date date_end, @Param("filmroom_id") long filmroom_id);

    @Query(value = "SELECT * from tbl_showtimes where filmroom_id = :id AND time_start >= :date", nativeQuery = true)
    List<ShowTimes> getListShowTimesByFilmId(@Param("id") long id, @Param("date") Date date);
}

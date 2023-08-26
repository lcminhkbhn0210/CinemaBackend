package com.example.cinema.management.repositories;

import com.example.cinema.management.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {

    @Query(value = "SELECT * FROM tbl_film WHERE name LIKE %:name%", nativeQuery = true)
    List<Film> findByNameIsLike(@Param("name") String name);

    @Override
    Page<Film> findAll(Pageable pageable);
}

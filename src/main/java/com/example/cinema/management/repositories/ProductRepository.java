package com.example.cinema.management.repositories;

import com.example.cinema.management.model.Product;
import com.example.cinema.management.model.SellProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);
    @Query(value = "SELECT * FROM tbl_product WHERE name LIKE %:name%", nativeQuery = true)
    List<Product> getProductsByName(@Param("name") String name);

    @Query(value = "SELECT * FROM tbl_product WHERE id = :id", nativeQuery = true)
    SellProduct getSellProductByID(@Param("id") long id);
}

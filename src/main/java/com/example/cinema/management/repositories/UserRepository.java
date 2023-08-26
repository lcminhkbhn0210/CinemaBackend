package com.example.cinema.management.repositories;

import com.example.cinema.management.model.Customer;
import com.example.cinema.management.model.Employee;
import com.example.cinema.management.model.Type;
import com.example.cinema.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByUsername(String username);

    Optional<User> findByUsername(String username);
    List<User> getUserByType(Type type);

    @Query(value = "SELECT * FROM tbl_user WHERE id = ?", nativeQuery = true)
    Optional<Employee> findEmployeeById(@Param("id") long id);

    User findByVerificationCode(String code);
    @Query(value = "SELECT * FROM tbl_user WHERE id = ?", nativeQuery = true)
    Optional<Customer> findCustomerById(@Param("id") long id);
}

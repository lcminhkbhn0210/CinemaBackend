package com.example.cinema.management.repositories;

import com.example.cinema.management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByAuthority(String authority);

    @Query(value = "SELECT roles.role_id, authority FROM roles join user_role_junction on roles.role_id = user_role_junction.role_id where user_role_junction.user_id = :userId", nativeQuery = true)
    Set<Role> findAllByUserId(@Param("userId") long userId);
}

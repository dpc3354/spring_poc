package com.employees.spring_boot_poc.repository;

import com.employees.spring_boot_poc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUserId(Long userId);
    Boolean existsByUsername(String username);

    Boolean existsByUserId(Long userId);

}

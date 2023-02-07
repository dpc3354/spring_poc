package com.employees.spring_boot_poc.repository;

import com.employees.spring_boot_poc.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}

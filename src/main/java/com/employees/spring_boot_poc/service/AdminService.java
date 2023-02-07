package com.employees.spring_boot_poc.service;

import com.employees.spring_boot_poc.entity.User;

import java.util.List;

public interface AdminService {

    String deleteUser(Long userId);

    List<User> findAll();

    User findById(Long userId);
}

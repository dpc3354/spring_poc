package com.employees.spring_boot_poc.service;


import com.employees.spring_boot_poc.entity.User;
import com.employees.spring_boot_poc.payload.LoginDto;
import com.employees.spring_boot_poc.payload.SignUpDto;


public interface AuthService {

    String login(LoginDto loginDto);

    String register(SignUpDto signUpDto);

    String update(User user);

}

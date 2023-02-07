package com.employees.spring_boot_poc.payload;


import lombok.Data;

@Data
public class LoginDto {

    private String username;
    private String password;
}

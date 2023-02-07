package com.employees.spring_boot_poc.payload;


import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String password;
    private String SSN;
}

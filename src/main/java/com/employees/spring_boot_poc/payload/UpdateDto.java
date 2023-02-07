package com.employees.spring_boot_poc.payload;

import lombok.Data;

@Data
public class UpdateDto {

    private Long userId;
    private String name;
    private String address;
    private String city;
    private String ssn;
    private String phone;

}

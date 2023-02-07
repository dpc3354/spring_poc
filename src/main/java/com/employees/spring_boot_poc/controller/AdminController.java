package com.employees.spring_boot_poc.controller;


import com.employees.spring_boot_poc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @DeleteMapping("/delete-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestParam(required = false) Long userId) {
        String response = adminService.deleteUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAllUser() {
        return new ResponseEntity<>(adminService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(adminService.findById(userId), HttpStatus.OK);
    }

}

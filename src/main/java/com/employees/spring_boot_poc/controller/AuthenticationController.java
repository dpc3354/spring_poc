package com.employees.spring_boot_poc.controller;


import com.employees.spring_boot_poc.entity.User;
import com.employees.spring_boot_poc.payload.JwtResponse;
import com.employees.spring_boot_poc.payload.LoginDto;
import com.employees.spring_boot_poc.payload.SignUpDto;
import com.employees.spring_boot_poc.service.AuthService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class AuthenticationController {


    @Autowired
    private AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody @NotNull LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtResponse);
    }

    @PutMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @NotNull SignUpDto signUpDto){
        String response = authService.register(signUpDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        String response = authService.update(user);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}

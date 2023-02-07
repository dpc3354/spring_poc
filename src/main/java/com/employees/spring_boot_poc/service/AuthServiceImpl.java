package com.employees.spring_boot_poc.service;

import com.employees.spring_boot_poc.config.JwtTokenUtil;
import com.employees.spring_boot_poc.entity.Role;
import com.employees.spring_boot_poc.entity.User;
import com.employees.spring_boot_poc.exception.PocAPIException;
import com.employees.spring_boot_poc.payload.LoginDto;
import com.employees.spring_boot_poc.payload.SignUpDto;
import com.employees.spring_boot_poc.repository.RoleRepository;
import com.employees.spring_boot_poc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public String login(LoginDto loginDto) {

        if (!userRepository.existsByUsername(loginDto.getUsername()))
            throw new PocAPIException(HttpStatus.BAD_REQUEST, "USERNAME DID NOT EXISTED!!");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenUtil.generateToken(authentication);
    }


    @Override
    public String register(SignUpDto signUpDto) {
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            throw new PocAPIException(HttpStatus.BAD_REQUEST, "USERNAME ALREADY EXISTED!!");
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            throw new PocAPIException(HttpStatus.BAD_REQUEST, "ROLE DOES NOT EXIST!!");
        }
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRole(userRole);

        userRepository.save(user);

        return "USER REGISTERED SUCCESSFULLY";
    }

    @Override
    public String update(User user) {

        Optional<User> oldUserOptional = userRepository.findByUserId(user.getUserId());
        if (oldUserOptional.isEmpty())
            throw new PocAPIException(HttpStatus.BAD_REQUEST, "USER DID NOT EXIST!!");
        User oldUser = oldUserOptional.get();
        user.setUsername(oldUser.getUsername());
        user.setPassword(oldUser.getPassword());
        user.setRole(oldUser.getRole());
        userRepository.save(user);

        return "USER INFORMATION UPDATED SUCCESSFULLY!!";
    }
}

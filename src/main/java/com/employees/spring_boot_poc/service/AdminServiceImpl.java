package com.employees.spring_boot_poc.service;

import com.employees.spring_boot_poc.entity.User;
import com.employees.spring_boot_poc.exception.PocAPIException;
import com.employees.spring_boot_poc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String deleteUser(Long userId) {
        if (!userRepository.existsByUserId(userId))
            throw new PocAPIException(HttpStatus.BAD_REQUEST, "USER DID NOT EXIST!!");
        userRepository.deleteById(userId);
        return "USER DELETE SUCCESSFULLY!!";
    }

    @Override
    public List<User> findAll() {
        List<User> userList = (List<User>) userRepository.findAll();
        for (User user : userList) user.setPassword(null);
        return userList;
    }

    @Override
    public User findById(Long userId) {

        Optional<User> user = userRepository.findByUserId(userId);

        if (user.isEmpty())
            throw new PocAPIException(HttpStatus.BAD_REQUEST, "USER DID NOT EXIST!!");

        user.get().setPassword(null);

        return user.get();
    }
}

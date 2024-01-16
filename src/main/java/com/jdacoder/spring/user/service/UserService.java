package com.jdacoder.spring.user.service;

import com.jdacoder.spring.user.model.User;
import com.jdacoder.spring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* All rights reserved by JdaCoder */

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);
        return users;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void updatePassword(String updatedPassword) {
        userRepository.findByEmail(updatedPassword);
    }
}


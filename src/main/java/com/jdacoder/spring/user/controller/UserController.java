package com.jdacoder.spring.user.controller;

import com.jdacoder.spring.user.model.User;
import com.jdacoder.spring.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/* All rights reserved by JdaCoder */

@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:3000", maxAge = 3600)
@RestController
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public Optional<User> getUserById(@PathVariable Long id) {
        return Optional.ofNullable(userService.getUserById(id));
    }

    @Override
    public void updateUser(@Valid @RequestBody User user) {
        userService.updateUser(user);
    }

    @Override
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}



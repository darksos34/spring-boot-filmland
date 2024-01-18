package com.jdacoder.spring.auth.controller;

import com.jdacoder.spring.auth.payload.request.LoginRequest;
import com.jdacoder.spring.auth.payload.request.RegisterRequest;
import com.jdacoder.spring.requests.RequestConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/* All rights reserved by JdaCoder */

@RequestMapping(RequestConstants.API + RequestConstants.AUTH)
public interface AuthApi {
    @PostMapping(RequestConstants.LOGIN)
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) ;

    @PostMapping(RequestConstants.REGISTER)
    ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest);
}

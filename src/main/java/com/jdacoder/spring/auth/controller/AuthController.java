package com.jdacoder.spring.auth.controller;

import com.jdacoder.spring.auth.payload.request.LoginRequest;
import com.jdacoder.spring.auth.payload.request.RegisterRequest;
import com.jdacoder.spring.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/* All rights reserved by JdaCoder */

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class AuthController implements AuthApi{

	private final AuthService authService;

	@Override
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return authService.getJwtResponseResponseEntity(loginRequest);
	}

	@Override
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
		return authService.checkAndRegisterUserDetailsWithRole(registerRequest);
	}
}

package com.jdacoder.spring.auth.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/* All rights reserved by JdaCoder */

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class TestController implements TestApi{

	@Override
	public String allAccess() {
		return "Public Content.";
	}

	@Override
	public String userAccess() {
		return "User Content.";
	}

	@Override
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@Override
	public String adminAccess() {
		return "Admin Board.";
	}

}

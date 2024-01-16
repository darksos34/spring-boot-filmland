package com.jdacoder.spring.auth.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/* All rights reserved by JdaCoder */

@Getter
@Setter
public class LoginRequest {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
}

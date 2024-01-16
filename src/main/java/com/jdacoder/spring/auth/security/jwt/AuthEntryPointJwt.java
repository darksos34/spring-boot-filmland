package com.jdacoder.spring.auth.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* All rights reserved by JdaCoder */

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		log.error("Unauthorized error: {}", authException.getMessage());

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Invalid credentials");
	}

}

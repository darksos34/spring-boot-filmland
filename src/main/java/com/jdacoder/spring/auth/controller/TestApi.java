package com.jdacoder.spring.auth.controller;

import com.jdacoder.spring.requests.RequestConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/* All rights reserved by JdaCoder */

@RequestMapping(RequestConstants.TEST)
public interface TestApi {
    @GetMapping(RequestConstants.ALL)
    String allAccess();

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    String userAccess();

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    String moderatorAccess();

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    String adminAccess();
}

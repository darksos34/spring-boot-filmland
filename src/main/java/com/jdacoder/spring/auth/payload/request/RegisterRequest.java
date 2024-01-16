package com.jdacoder.spring.auth.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/* All rights reserved by JdaCoder */

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    @Size(min = 3, max = 25)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private Set<String> role;

    @NotBlank
    @Size(min = 8, max = 125)
    private String password;

}

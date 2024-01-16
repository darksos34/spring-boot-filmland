package com.jdacoder.spring.role.service;

import com.jdacoder.spring.role.model.ERole;
import com.jdacoder.spring.role.model.Role;
import com.jdacoder.spring.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

/* All rights reserved by JdaCoder */

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> getRoleByName(ERole name) {
        return roleRepository.getRoleByName(name);
    }

}

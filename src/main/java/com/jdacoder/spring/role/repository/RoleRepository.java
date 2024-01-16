package com.jdacoder.spring.role.repository;


import com.jdacoder.spring.role.model.ERole;
import com.jdacoder.spring.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* All rights reserved by JdaCoder */

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> getRoleByName(ERole name);

}

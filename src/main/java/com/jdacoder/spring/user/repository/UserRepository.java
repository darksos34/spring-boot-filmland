package com.jdacoder.spring.user.repository;

import com.jdacoder.spring.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/* All rights reserved by JdaCoder */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :userId")
    void updatePassword(@Param("password") String password, @Param("userId") Long userId);
}

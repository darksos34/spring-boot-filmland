package com.jdacoder.spring.subscribe.repository;
import com.jdacoder.spring.subscribe.model.entiteiten.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* All rights reserved by JdaCoder */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
}


package com.jdacoder.spring.subscribe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdacoder.spring.subscribe.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContainingIgnoreCase(String title);
}

package com.jdacoder.spring.subscribe.repository;

import com.jdacoder.spring.subscribe.model.Category;
import com.jdacoder.spring.subscribe.model.Subscription;
import com.jdacoder.spring.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* All rights reserved by JdaCoder */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    boolean existsByUserAndCategory(User user, Category category);

}

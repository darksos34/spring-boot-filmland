package com.jdacoder.spring.subscribe.service;

import com.jdacoder.spring.subscribe.model.entiteiten.Category;
import com.jdacoder.spring.subscribe.model.dto.SubscribeDTO;
import com.jdacoder.spring.subscribe.model.entiteiten.Subscription;
import com.jdacoder.spring.subscribe.repository.SubscriptionRepository;
import com.jdacoder.spring.user.model.User;
import com.jdacoder.spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/* All rights reserved by JdaCoder */

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository,
                               UserService userService,
                               CategoryService categoryService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public SubscribeDTO subscribe(Long userId, Long categoryId) {
        User user = userService.getUserById(userId);
        Category category = categoryService.getCategoryById(categoryId);

        if (subscriptionRepository.existsByUserAndCategory(user, category)) {
            throw new IllegalStateException("User is already subscribed to the category.");
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setCategory(category);
        subscription.setPrice(category.getPrice());
        subscription.setStartDate(LocalDate.now().toString());

        Subscription savedSubscription = subscriptionRepository.save(subscription);

        return mapToSubscribeDTO(savedSubscription);
    }

    private SubscribeDTO mapToSubscribeDTO(Subscription subscription) {
        SubscribeDTO dto = new SubscribeDTO();
        dto.setUserId(subscription.getUser().getId());
        dto.setCategoryId(subscription.getCategory().getId());
        dto.setCategoryName(subscription.getCategory().getName());
        dto.setRemainingContent(subscription.getRemainingContent());
        dto.setPrice(subscription.getPrice());
        dto.setStartDate(subscription.getStartDate());

        return dto;
    }
}

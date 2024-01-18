package com.jdacoder.spring.subscribe.controller;

import com.jdacoder.spring.subscribe.model.Category;
import com.jdacoder.spring.subscribe.model.SubscribeDTO;
import com.jdacoder.spring.subscribe.service.CategoryService;
import com.jdacoder.spring.subscribe.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final CategoryService categoryService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, CategoryService categoryService) {
        this.subscriptionService = subscriptionService;
        this.categoryService = categoryService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<SubscribeDTO> subscribeToCategory(@RequestBody SubscribeDTO subscribeDTO) {
        SubscribeDTO subscribeResponse = subscriptionService.subscribe(subscribeDTO.getUserId(), subscribeDTO.getCategoryId());
        return ResponseEntity.ok(subscribeResponse);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        try {
            Category category = categoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(category);
        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

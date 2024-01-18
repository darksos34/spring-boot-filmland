package com.jdacoder.spring.subscribe.service;
import com.jdacoder.spring.subscribe.model.entiteiten.Category;
import com.jdacoder.spring.subscribe.repository.CategoryRepository;

import com.jdacoder.spring.user.model.User;
import com.jdacoder.spring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/* All rights reserved by JdaCoder */

@Service
@RequiredArgsConstructor
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
    private UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        LOGGER.info("Attempting to retrieve category with ID: {}", categoryId);
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
    }

    public boolean isCategoryAvailable(Long categoryId) {
        Category category = getCategoryById(categoryId);

        return !category.isOutOfStock();
    }

    public Category createCategory(Category category) {

        return categoryRepository.save(category);
    }

    public ResponseEntity<String> shareCategory(Long categoryId, String recipientUsername, Long userId, User loggedInUser) {
        Category categoryToShare = categoryRepository.findById(categoryId).orElse(null);
        User recipientUser = userRepository.getUserByUsername(recipientUsername).orElse(null);
        User sharingUser = userRepository.findById(userId).orElse(null);

        if (categoryToShare != null && recipientUser != null && sharingUser != null && isUserSubscribed(loggedInUser)) {
            boolean sharedSuccessfully = performSharingLogic(categoryToShare, recipientUser, sharingUser, loggedInUser);

            if (sharedSuccessfully) {
                return new ResponseEntity<>("Category shared successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to share category", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
    private boolean isUserSubscribed(User user) {
        return userRepository.isUserSubscribed(user.getId());
    }

    private boolean performSharingLogic(Category categoryToShare, User recipientUser, User sharingUser, User loggedInUser) {

        if (categoryToShare.getUser().equals(sharingUser)) {
            try {

                System.out.println("Category shared successfully!");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Category shared unsuccessfully!");
                return false;
            }
        }
        return false;
    }



}

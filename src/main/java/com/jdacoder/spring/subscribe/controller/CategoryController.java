package com.jdacoder.spring.subscribe.controller;

import com.jdacoder.spring.subscribe.model.Category;
import com.jdacoder.spring.subscribe.model.ShareCategoryDTO;
import com.jdacoder.spring.subscribe.service.CategoryService;
import com.jdacoder.spring.user.model.User;
import com.jdacoder.spring.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* All rights reserved by JdaCoder */

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final UserService userService;
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PostMapping("/share")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> shareCategory(@RequestBody ShareCategoryDTO shareCategoryDTO, Long id) {
        User loggedInUser = userService.getUserById(id);

        return categoryService.shareCategory(shareCategoryDTO.getCategoryId(), shareCategoryDTO.getRecipientUsername(), shareCategoryDTO.getSharingUserId(), loggedInUser);
    }

}

package com.jdacoder.spring.subscribe.service;
import com.jdacoder.spring.subscribe.model.Category;
import com.jdacoder.spring.subscribe.repository.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

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
}

package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.part.CategoryDto;
import com.crocostaud.stockmanagement.model.part.Category;
import com.crocostaud.stockmanagement.repository.CategoryRepository;
import com.crocostaud.stockmanagement.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        Category category = categoryRepo.findById(id)
                .orElse(null);
        if (category == null)
            return null;

        return CategoryDto.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}

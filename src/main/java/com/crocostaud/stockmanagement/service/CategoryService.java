package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.CategoryDto;

public interface CategoryService {

    CategoryDto getCategoryById(long id);
}

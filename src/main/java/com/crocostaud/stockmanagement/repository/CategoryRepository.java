package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findDistinctCategoryName();
}
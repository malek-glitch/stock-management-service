package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
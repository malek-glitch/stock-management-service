package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("""
             select distinct c.name from Category c
            """)
    List<String> findDistinctCategoryName();


}
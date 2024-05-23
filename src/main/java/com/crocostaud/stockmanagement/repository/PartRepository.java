package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Category;
import com.crocostaud.stockmanagement.model.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long> {
    List<Part> findByNameLikeIgnoreCase(String name);

    List<Part> findByRefLikeIgnoreCase(String ref);

    List<Part> findByCategory(Category category);
}
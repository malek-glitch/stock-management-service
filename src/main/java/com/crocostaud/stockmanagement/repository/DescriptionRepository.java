package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
}
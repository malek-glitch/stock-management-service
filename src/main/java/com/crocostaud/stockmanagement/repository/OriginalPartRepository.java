package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Original;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OriginalPartRepository extends JpaRepository<Original, String> {
}
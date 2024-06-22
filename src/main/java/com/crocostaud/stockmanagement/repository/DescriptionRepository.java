package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
    @Query("select d from description d where d.part.id = ?1")
    List<Description> findByPart_Id(Long id);
}
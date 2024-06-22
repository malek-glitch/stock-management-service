package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Original;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OriginalPartRepository extends JpaRepository<Original, String> {
    @Query("select o from original o inner join o.parts parts where parts.id = ?1")
    List<Original> findByParts_Id(Long id);
}
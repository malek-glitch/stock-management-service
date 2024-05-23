package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.dto.part.ModelDto;
import com.crocostaud.stockmanagement.model.part.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<ModelDto> findByMaker_Id(Long id);
}
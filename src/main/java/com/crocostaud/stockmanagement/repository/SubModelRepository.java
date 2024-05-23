package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.dto.stock.SubModelDto;
import com.crocostaud.stockmanagement.model.part.SubModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubModelRepository extends JpaRepository<SubModel, Long> {
    List<SubModelDto> findByModel_Id(Long id);
}
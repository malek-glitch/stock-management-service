package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Maker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakerRepository extends JpaRepository<Maker, Long> {
}
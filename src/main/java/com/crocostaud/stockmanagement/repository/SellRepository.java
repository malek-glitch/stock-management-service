package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long> {
}
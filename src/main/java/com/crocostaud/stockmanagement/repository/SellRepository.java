package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long> {
}
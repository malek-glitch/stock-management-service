package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.SellItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellItemRepository extends JpaRepository<SellItem, Long> {
}
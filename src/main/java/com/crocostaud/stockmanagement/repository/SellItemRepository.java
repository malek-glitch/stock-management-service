package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.SellItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellItemRepository extends JpaRepository<SellItem, Long> {
}
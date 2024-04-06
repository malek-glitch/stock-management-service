package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
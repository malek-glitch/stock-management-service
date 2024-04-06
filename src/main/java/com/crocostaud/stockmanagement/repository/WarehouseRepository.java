package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
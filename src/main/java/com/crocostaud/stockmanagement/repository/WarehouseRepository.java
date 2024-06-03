package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Warehouse findByShop_IdAndIsDefault(Long id, boolean isDefault);
}
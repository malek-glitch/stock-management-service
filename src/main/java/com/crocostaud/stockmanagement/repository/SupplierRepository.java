package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, String> {
}
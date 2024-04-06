package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    @Modifying
    @Query("update Product p set p.ref = ?1, p.name = ?2, p.SupplierName = ?3, p.description = ?4 where p.id = ?5")
    int updateRefAndNameAndSupplierNameAndDescriptionById(String ref, String name, String SupplierName, String description, Long id);
}
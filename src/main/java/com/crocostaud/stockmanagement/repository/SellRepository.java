package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellRepository extends JpaRepository<Sell, Long> {
    @Query("select s from Sell s where s.shop.id = ?1")
    List<Sell> findByShop(Long id);
}
package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.SellItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellItemRepository extends JpaRepository<SellItem, Long> {
    @Query("select s from sell_item s where s.sell.id = ?1")
    List<SellItem> findBySellId(Long id);

    @Query("select s from sell_item s where s.sell.id = ?1 and s.id = ?2")
    SellItem findBySell_IdAndId(Long id, Long id1);
}
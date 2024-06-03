package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Transactional
    @Modifying
    @Query("update orders o set o.discount = ?1, o.paidAmount = ?2, o.date = ?3 where o.id = ?4")
    void updateDiscountAndPaidAmountAndDateById(int discount, Double paidAmount, LocalDateTime date, Long id);

    @Query("select o from orders o where o.shop.id = ?1")
    List<Order> findByShop_Id(Long id);
}
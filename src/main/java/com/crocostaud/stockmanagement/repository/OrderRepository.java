package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Transactional
    @Modifying
    @Query("update orders o set o.discount = ?1, o.paidAmount = ?2, o.date = ?3 where o.id = ?4")
    int updateDiscountAndPaidAmountAndDateById(int discount, Double paidAmount, Date date, Long id);
}
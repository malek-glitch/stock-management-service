package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("select o from order_item o where o.order.id = ?1")
    List<OrderItem> findByOrder_Id(Long id);
}
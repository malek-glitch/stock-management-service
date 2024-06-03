package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getAllItems(Long orderId);

    OrderItemDto createOrderItem(OrderItemDto orderItemDto, Long shopId);
    OrderItemDto updateOrderItem(OrderItemDto orderItemDto, Long id);
    void deleteOrderItem(Long id);
}

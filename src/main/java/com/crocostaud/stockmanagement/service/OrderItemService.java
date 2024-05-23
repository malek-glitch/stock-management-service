package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;

public interface OrderItemService {
    OrderItemDto createOrderItem(OrderItemDto orderItemDto);
    OrderItemDto updateOrderItem(OrderItemDto orderItemDto, Long id);
    void deleteOrderItem(Long id);

}

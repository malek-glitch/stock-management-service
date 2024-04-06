package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.OrderItemDto;
import com.crocostaud.stockmanagement.model.OrderItem;

public interface OrderItemService {
    OrderItemDto createOrderItem(OrderItemDto orderItemDto);
    OrderItemDto updateOrderItem(OrderItemDto orderItemDto, Long id);
    void deleteOrderItem(Long id);

}

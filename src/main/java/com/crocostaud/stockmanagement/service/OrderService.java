package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.OrderDto;
import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto, List<OrderItemDto> orderItems);

    void addItemsToOrder(List<OrderItemDto> orderItems, Long OrderId);

    OrderDto updateOrder(OrderDto orderDto, Long orderId);

    OrderDto getOrder(Long orderId);

    void deleteOrder(Long id);
}

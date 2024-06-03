package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.OrderDto;
import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getOrders(Long shopId);

    OrderDto getOrder(Long orderId);

    List<OrderItemDto> getOrderItems(Long orderId);
    OrderDto createOrder(OrderDto orderDto, List<OrderItemDto> orderItems);

    void addItemsToOrder(List<OrderItemDto> orderItems, Long OrderId, Long shopId);
    OrderDto updateOrder(OrderDto orderDto, Long orderId);
    void deleteOrder(Long id);
}

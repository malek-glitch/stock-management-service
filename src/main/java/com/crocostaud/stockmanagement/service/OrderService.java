package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto updateOrder(OrderDto orderDto, Long orderId);
    OrderDto getOrder(Long orderId);
    void deleteOrder(Long id);

}

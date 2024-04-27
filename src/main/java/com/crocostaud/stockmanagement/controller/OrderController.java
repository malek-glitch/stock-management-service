package com.crocostaud.stockmanagement.controller;


import com.crocostaud.stockmanagement.dto.OrderDto;
import com.crocostaud.stockmanagement.dto.OrderItemDto;
import com.crocostaud.stockmanagement.service.OrderService;
import com.crocostaud.stockmanagement.util.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<OrderDto> create(@RequestBody OrderRequest orderRequest) {
        OrderDto savedOrder =
                orderService.createOrder(
                        orderRequest.getOrderDto(),
                        orderRequest.getOrderItems()
                );
        return ResponseEntity.ok(savedOrder);
    }

    @PostMapping("/{orderId}/addItem")
    ResponseEntity<Void> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDto itemDto) {

        if (orderService.getOrder(orderId) == null)
            return ResponseEntity.noContent().build();

        orderService.addItemsToOrder(List.of(itemDto), orderId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> get(@PathVariable Long orderId) {
        OrderDto order = orderService.getOrder(orderId);
        if (order == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{orderId}")
    ResponseEntity<Void> delete(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

}



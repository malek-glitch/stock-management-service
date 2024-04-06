package com.crocostaud.stockmanagement.controller;


import com.crocostaud.stockmanagement.dto.OrderDto;
import com.crocostaud.stockmanagement.dto.OrderItemDto;
import com.crocostaud.stockmanagement.repository.OrderRepository;
import com.crocostaud.stockmanagement.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto, @RequestBody List<OrderItemDto> orderItems) {
        OrderDto savedOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(savedOrder);
    }

    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getAll(@PathVariable Long orderId){
        OrderDto order = orderService.getOrder(orderId);
        if (order == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(order);
    }

}

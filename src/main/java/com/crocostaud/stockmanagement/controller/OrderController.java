package com.crocostaud.stockmanagement.controller;


import com.crocostaud.stockmanagement.dto.OrderDto;
import com.crocostaud.stockmanagement.service.OrderService;
import com.crocostaud.stockmanagement.util.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<OrderDto> create(@RequestBody OrderRequest orderRequest) {
        OrderDto savedOrder = orderService.createOrder(orderRequest.getOrderDto(), orderRequest.getOrderItems());
        return ResponseEntity.ok(savedOrder);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.createOrder(orderDto, null);
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



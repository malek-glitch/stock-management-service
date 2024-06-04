package com.crocostaud.stockmanagement.controller;


import com.crocostaud.stockmanagement.dto.stock.OrderDto;
import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.OrderService;
import com.crocostaud.stockmanagement.utils.annotation.User;
import com.crocostaud.stockmanagement.utils.request.OrderRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderRequest> get(@PathVariable Long orderId, @User ShopUser user) {

        if (user == null || user.getShop() == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "unauthorized");
        OrderDto order = orderService.getOrder(orderId);
        if (order == null || !Objects.equals(order.getShopId(), user.getShop().getId()))
            return ResponseEntity.noContent().build();
        List<OrderItemDto> items = orderService.getOrderItems(orderId);
        OrderRequest orderRequest = new OrderRequest(order, items);

        return ResponseEntity.ok(orderRequest);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders(@User ShopUser user) {
        System.out.println(user);
        if (user == null || user.getShop() == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Unauthorized");
        List<OrderDto> orders = orderService.getOrders(user.getShop().getId());
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderRequest orderRequest, @User ShopUser user) {
        if (user == null || user.getShop() == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Unauthorized");
        orderRequest.orderDto().setShopId(user.getShop().getId());
        OrderDto savedOrder =
                orderService.createOrder(
                        orderRequest.orderDto(),
                        orderRequest.orderItems()
                );
        return ResponseEntity.ok(savedOrder);
    }

    @PostMapping("/{orderId}/add_items")
    ResponseEntity<Void> addOrderItem(@PathVariable Long orderId, @RequestBody List<OrderItemDto> itemsDto, @User ShopUser user) {
        if (user == null || user.getShop() == null)
            return ResponseEntity.badRequest().build();
        OrderDto order = orderService.getOrder(orderId);
        if (order == null || !Objects.equals(order.getShopId(), user.getShop().getId()))
            return ResponseEntity.noContent().build();

        orderService.addItemsToOrder(itemsDto, orderId, user.getShop().getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{orderId}")
    ResponseEntity<Void> delete(@PathVariable Long orderId, @User ShopUser user) {
        if (user == null || user.getShop() == null)
            return ResponseEntity.badRequest().build();
        OrderDto order = orderService.getOrder(orderId);
        if (order == null || !Objects.equals(user.getShop().getId(), order.getShopId()))
            return ResponseEntity.noContent().build();
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}



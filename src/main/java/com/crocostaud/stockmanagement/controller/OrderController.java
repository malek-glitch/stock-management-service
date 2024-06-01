package com.crocostaud.stockmanagement.controller;


import com.crocostaud.stockmanagement.dto.stock.OrderDto;
import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.OrderService;
import com.crocostaud.stockmanagement.utils.annotation.Username;
import com.crocostaud.stockmanagement.utils.request.OrderRequest;
import com.crocostaud.stockmanagement.utils.security.Auth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final Auth auth;

    public OrderController(OrderService orderService, Auth auth) {
        this.orderService = orderService;
        this.auth = auth;
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderRequest orderRequest, @Username String username) {
        ShopUser user = auth.getUser(username);
        if (user == null || user.getShop() == null)
            return ResponseEntity.badRequest().build();
        orderRequest.getOrderDto().setShopId(user.getShop().getId());
        OrderDto savedOrder =
                orderService.createOrder(
                        orderRequest.getOrderDto(),
                        orderRequest.getOrderItems()
                );
        return ResponseEntity.ok(savedOrder);
    }

    @PostMapping("/{orderId}/add_items")
    ResponseEntity<Void> addOrderItem(@PathVariable Long orderId, @RequestBody List<OrderItemDto> itemsDto, @Username String username) {
        ShopUser user = auth.getUser(username);
        if (user == null || user.getShop() == null)
            return ResponseEntity.badRequest().build();
        OrderDto order = orderService.getOrder(orderId);
        if (order == null || !Objects.equals(order.getShopId(), user.getShop().getId()))
            return ResponseEntity.noContent().build();

        orderService.addItemsToOrder(itemsDto, orderId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> get(@PathVariable Long orderId, @Username String username) {
        ShopUser user = auth.getUser(username);
        if (user == null || user.getShop() == null)
            return ResponseEntity.badRequest().build();
        OrderDto order = orderService.getOrder(orderId);
        if (order == null || !Objects.equals(order.getShopId(), user.getShop().getId()))
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{orderId}")
    ResponseEntity<Void> delete(@PathVariable Long orderId, @Username String username) {
        ShopUser user = auth.getUser(username);
        if (user == null || user.getShop() == null)
            return ResponseEntity.badRequest().build();
        OrderDto order = orderService.getOrder(orderId);
        if (order == null || !Objects.equals(user.getShop().getId(), order.getShopId()))
            return ResponseEntity.noContent().build();
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

}



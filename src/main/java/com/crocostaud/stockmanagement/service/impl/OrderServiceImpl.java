package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.OrderDto;
import com.crocostaud.stockmanagement.model.Order;
import com.crocostaud.stockmanagement.model.Provider;
import com.crocostaud.stockmanagement.model.Shop;
import com.crocostaud.stockmanagement.repository.OrderRepository;
import com.crocostaud.stockmanagement.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;

    public OrderServiceImpl(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .provider(new Provider(orderDto.getProviderId()))
                .shop(new Shop(orderDto.getShopId()))
                .paidAmount(orderDto.getPaidAmount())
                .discount(orderDto.getDiscount())
                .date(orderDto.getDate()).build();
        Order savedOrder = orderRepo.save(order);
        return mapToDto(savedOrder);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, Long id) {
        Optional<Order> orderOptional = orderRepo.findById(id);
        if (orderOptional.isEmpty()) return null;
        orderRepo.updateDiscountAndPaidAmountAndDateById(orderDto.getDiscount(), orderDto.getPaidAmount(), orderDto.getDate(), id);
        return mapToDto(orderRepo.findById(id).get());
    }

    public OrderDto getOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
        return optionalOrder.map(this::mapToDto).orElse(null);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

    private OrderDto mapToDto(Order order) {
        return OrderDto.builder().id(order.getId())
                .providerId(order.getProvider().getId())
                .shopId(order.getShop().getId())
                .date(order.getDate())
                .paidAmount(order.getPaidAmount())
                .discount(order.getDiscount())
                .build();
    }
}

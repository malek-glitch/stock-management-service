package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.OrderDto;
import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;
import com.crocostaud.stockmanagement.model.part.Part;
import com.crocostaud.stockmanagement.model.stock.Order;
import com.crocostaud.stockmanagement.model.stock.Provider;
import com.crocostaud.stockmanagement.model.stock.Shop;
import com.crocostaud.stockmanagement.repository.OrderRepository;
import com.crocostaud.stockmanagement.service.OrderItemService;
import com.crocostaud.stockmanagement.service.OrderService;
import com.crocostaud.stockmanagement.service.PartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemService orderItemService;
    private final PartService partService;

    public OrderServiceImpl(OrderRepository orderRepo, OrderItemService orderItemService, PartService partService) {
        this.orderRepo = orderRepo;
        this.orderItemService = orderItemService;
        this.partService = partService;
    }

    @Override
    public List<OrderDto> getOrders(Long shopId) {
        return orderRepo.findByShop_Id(shopId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto, List<OrderItemDto> orderItems) {
        Order order = Order.builder()
                .provider(new Provider(orderDto.getProviderId()))
                .shop(new Shop(orderDto.getShopId()))
                .totalPrice(calculateTotalPrice(orderItems))
                .paidAmount(orderDto.getPaidAmount())
                .discount(orderDto.getDiscount())
                .date(LocalDateTime.now())
                .build();
        Order savedOrder = orderRepo.save(order);
        addItemsToOrder(orderItems, order.getId(), order.getShop().getId());
        return mapToDto(savedOrder);
    }

    @Override
    public void addItemsToOrder(List<OrderItemDto> orderItems, Long OrderId, Long ShopId) {
        for (OrderItemDto item : orderItems) {
            Part part = partService.getPart(item.getPartId());
            item.setOrderId(OrderId);
            item.setPartName(part.getName());
            orderItemService.createOrderItem(item, ShopId);
        }
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, Long id) {
        Optional<Order> orderOptional = orderRepo.findById(id);
        if (orderOptional.isEmpty())
            return null;
        orderRepo.updateDiscountAndPaidAmountAndDateById(orderDto.getDiscount(), orderDto.getPaidAmount(), orderDto.getDate(), id);
        return mapToDto(orderRepo.findById(id).get());
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
        return optionalOrder.map(this::mapToDto).orElse(null);
    }

    @Override
    public List<OrderItemDto> getOrderItems(Long orderId) {
        return orderItemService.getAllItems(orderId);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

    private Double calculateTotalPrice(List<OrderItemDto> orderItems) {
        return orderItems.stream().mapToDouble(item -> {
            double price = (item.getPrice() + item.getPrice() * item.getTVA() / 100) * item.getQuantity();
            double discount = price * item.getDiscount() / 100;
            return price - discount;
        }).sum();
    }

    private OrderDto mapToDto(Order order) {
        return OrderDto.builder().id(order.getId())
                .providerId(order.getProvider().getId())
                .shopId(order.getShop().getId())
                .date(order.getDate())
                .totalPrice(order.getTotalPrice())
                .paidAmount(order.getPaidAmount())
                .discount(order.getDiscount())
                .build();
    }
}

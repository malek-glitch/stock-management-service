package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;
import com.crocostaud.stockmanagement.model.part.Part;
import com.crocostaud.stockmanagement.model.stock.Order;
import com.crocostaud.stockmanagement.model.stock.OrderItem;
import com.crocostaud.stockmanagement.repository.OrderItemRepository;
import com.crocostaud.stockmanagement.service.OrderItemService;
import org.springframework.stereotype.Service;


@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepo;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepo) {
        this.orderItemRepo = orderItemRepo;

    }

    @Override
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        Part part = null;

        OrderItem orderItem = OrderItem.builder()
                .part(part)
                .order(new Order(orderItemDto.getOrderId()))
                .price(orderItemDto.getPrice())
                .quantity(orderItemDto.getQuantity())
                .TVA(orderItemDto.getTVA())
                .discount(orderItemDto.getDiscount())
                .build();
        OrderItem savedOrderItem = orderItemRepo.save(orderItem);
        return mapToDto(savedOrderItem);
    }

    private OrderItemDto mapToDto(OrderItem orderItem) {
        /*
        TODO: cleanup

        Order order = orderItem.getOrder();
        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .providerId(order.getProvider().getId())
                .build();

        Product product = orderItem.getProduct();
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .ref(product.getRef())
                .name(product.getName())
                .image(product.getImage())
                .description(product.getDescription())
                .SupplierName(product.getSupplierName())
                .build();
*/

        return OrderItemDto.builder()
                .orderId(orderItem.getOrder().getId())
                .partId(orderItem.getPart().getId())
                .id(orderItem.getId())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .TVA(orderItem.getTVA())
                .discount(orderItem.getDiscount())
                .build();
    }

    @Override
    public OrderItemDto updateOrderItem(OrderItemDto orderItem, Long id) {
        return null;
        //TODO : impl
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepo.deleteById(id);
    }
}

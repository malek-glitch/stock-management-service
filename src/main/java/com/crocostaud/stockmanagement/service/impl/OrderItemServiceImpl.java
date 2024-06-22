package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;
import com.crocostaud.stockmanagement.model.part.Part;
import com.crocostaud.stockmanagement.model.stock.Order;
import com.crocostaud.stockmanagement.model.stock.OrderItem;
import com.crocostaud.stockmanagement.repository.OrderItemRepository;
import com.crocostaud.stockmanagement.service.InventoryService;
import com.crocostaud.stockmanagement.service.OrderItemService;
import com.crocostaud.stockmanagement.service.PartService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepo;
    private final PartService partService;
    private final InventoryService inventoryService;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepo, PartService partService, InventoryService inventoryService) {
        this.orderItemRepo = orderItemRepo;
        this.partService = partService;
        this.inventoryService = inventoryService;
    }

    @Override
    public List<OrderItemDto> getAllItems(Long orderId) {
        return orderItemRepo.findByOrder_Id(orderId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto, Long shopId) {
        Part part = partService.getPart(orderItemDto.getPartId());
        if (part == null)
            throw new RuntimeException("Part not found");

        OrderItem orderItem = OrderItem.builder()
                .part(part)
                .order(new Order(orderItemDto.getOrderId()))
                .price(orderItemDto.getPrice())
                .quantity(orderItemDto.getQuantity())
                .TVA(orderItemDto.getTVA())
                .benefice(orderItemDto.getBenefice())
                .build();
        OrderItem savedOrderItem = orderItemRepo.save(orderItem);

        inventoryService.createInventory(orderItemDto, shopId);
        return mapToDto(savedOrderItem);
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

    private OrderItemDto mapToDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                .orderId(orderItem.getOrder().getId())
                .partId(orderItem.getPart().getId())
                .partName(orderItem.getPart().getName())
                .id(orderItem.getId())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .TVA(orderItem.getTVA())
                .benefice(orderItem.getBenefice())
                .build();
    }
}

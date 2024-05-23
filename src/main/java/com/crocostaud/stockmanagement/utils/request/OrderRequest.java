package com.crocostaud.stockmanagement.utils.request;

import com.crocostaud.stockmanagement.dto.stock.OrderDto;
import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;
import lombok.Getter;

import java.util.List;

/**
 * a wrapper class that is used as Data Transfert Object (DTO) for the order request it wrappers {@link OrderDto} and a {@link List } of {@link OrderItemDto}
 */
@Getter
public class OrderRequest {
    private OrderDto orderDto;
    private List<OrderItemDto> orderItems;
}

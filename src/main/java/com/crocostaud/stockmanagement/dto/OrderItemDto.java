package com.crocostaud.stockmanagement.dto;

import com.crocostaud.stockmanagement.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link OrderItem}
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class OrderItemDto implements Serializable {

    private Long id;
    private int quantity;
    private Double price;
    private int TVA;
    private int discount;

    private ProductDto product;
    private OrderDto orderDto;
}
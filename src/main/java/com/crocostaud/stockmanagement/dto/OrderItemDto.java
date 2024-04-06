package com.crocostaud.stockmanagement.dto;

import com.crocostaud.stockmanagement.model.OrderItem;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link OrderItem}
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class OrderItemDto implements Serializable {

    private Long id;
    private int quantity;
    private Double price;
    private int TVA;
    private int discount;

    private Long productId;
    private Long orderId;
}
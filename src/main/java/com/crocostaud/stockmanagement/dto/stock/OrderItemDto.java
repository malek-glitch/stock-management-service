package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.OrderItem;
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

    private Long partId;
    private Long orderId;
}
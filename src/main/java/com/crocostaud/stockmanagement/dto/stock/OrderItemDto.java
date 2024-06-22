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
public final class OrderItemDto implements Serializable {

    private Long id;
    private int quantity;
    private Double price;
    private String partName;
    private int TVA;
    private int benefice;

    private Long partId;
    private Long orderId;
}
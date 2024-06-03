package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.Order;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Order}
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public final class OrderDto implements Serializable {

    private final Long id;
    private LocalDateTime date;
    private Double totalPrice;
    private Double paidAmount;
    private int discount;

    private Long providerId;
    private Long shopId;
}
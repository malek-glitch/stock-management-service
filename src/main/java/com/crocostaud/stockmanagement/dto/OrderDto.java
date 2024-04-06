package com.crocostaud.stockmanagement.dto;

import com.crocostaud.stockmanagement.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Order}
 */
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class OrderDto implements Serializable {

    private final Long id;
    private LocalDateTime date;
    private Double totalPrice;
    private Double paidAmount;
    private int discount;

    private Long providerId;
    private Long shopId;
}
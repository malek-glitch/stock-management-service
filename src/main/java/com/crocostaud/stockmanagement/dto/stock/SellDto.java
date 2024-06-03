package com.crocostaud.stockmanagement.dto.stock;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.stock.Sell}
 */


@Builder
public record SellDto(
        Long id,
        LocalDateTime date,
        Double totalPrice,
        int discount,
        Double paidAmount,
        Long clientId,
        Long shopId
) implements Serializable {
}
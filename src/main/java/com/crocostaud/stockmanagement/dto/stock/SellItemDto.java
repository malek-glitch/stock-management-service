package com.crocostaud.stockmanagement.dto.stock;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public final class SellItemDto implements Serializable {
    private Long id;
    private int quantity;
    private Double price;
    private String partName;
    private int TVA;
    private int discount;

    private Long partId;
    private Long sellId;
}

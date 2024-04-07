package com.crocostaud.stockmanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.Inventory}
 */
@Setter
@Getter
@Builder
public class InventoryDto implements Serializable {
    private Long id;
    private int quantityAvailable;
    private int minimumStockQuantity;
    private Double price;
    private int tva;

    Long productId;
    Long shopId;
    Long warehouseId;
}
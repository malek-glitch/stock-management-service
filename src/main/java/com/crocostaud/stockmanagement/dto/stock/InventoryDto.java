package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.Inventory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link Inventory}
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

    Long partId;
    Long shopId;
    Long warehouseId;
}
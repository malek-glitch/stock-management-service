package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.Inventory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for {@link Inventory}
 */
@Setter
@Getter
@ToString
@Builder
public final class InventoryDto implements Serializable {
    private Long id;
    private String partName;
    private int quantityAvailable;
    private int minimumStockQuantity;
    private Double price;
    private int tva;

    Long partId;
    Long shopId;
    Long warehouseId;
}
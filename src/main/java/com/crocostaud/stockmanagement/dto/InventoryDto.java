package com.crocostaud.stockmanagement.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.Inventory}
 */
@Value
@Builder
public class InventoryDto implements Serializable {
    Long id;
    int quantityAvailable;
    int minimumStockQuantity;
    Long productId;

}
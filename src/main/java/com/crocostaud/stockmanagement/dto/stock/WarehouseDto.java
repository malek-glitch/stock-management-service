package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.Warehouse;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Warehouse}
 */
@Value
@Builder
public class WarehouseDto implements Serializable {
    Long id;
    String name;
    String locationName;
    String locationAddress;
}
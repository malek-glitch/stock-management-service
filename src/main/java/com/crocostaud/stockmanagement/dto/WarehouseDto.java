package com.crocostaud.stockmanagement.dto;

import com.crocostaud.stockmanagement.model.Warehouse;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Warehouse}
 */
@Value
public class WarehouseDto implements Serializable {
    Long id;
    String name;
}
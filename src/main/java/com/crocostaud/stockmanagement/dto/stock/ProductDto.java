package com.crocostaud.stockmanagement.dto.stock;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.part.Part}
 */
@Value
@Builder
public class ProductDto implements Serializable {
    Long   id;
    String ref;
    String name;
    String SupplierName;
    String description;
    String image;
}
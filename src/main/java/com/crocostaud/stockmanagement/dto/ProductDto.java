package com.crocostaud.stockmanagement.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.Product}
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
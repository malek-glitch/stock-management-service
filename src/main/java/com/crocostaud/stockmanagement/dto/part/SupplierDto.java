package com.crocostaud.stockmanagement.dto.part;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.part.Supplier}
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class SupplierDto implements Serializable {
    private final String name;
    private final String image;
    private final String description;
}
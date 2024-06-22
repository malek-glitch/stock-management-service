package com.crocostaud.stockmanagement.dto.part;

import com.crocostaud.stockmanagement.dto.stock.InventoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.part.Part}
 */
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class PartDetail implements Serializable {
    private final Long id;
    private final String ref;
    private final String name;
    private final String imageUrl;
    private final CategoryDto category;
    private final List<DescriptionDto> descriptions;
    private final List<OriginalPartDto> originals;
    private final SupplierDto supplier;
    private final List<InventoryDto> inventories;
    private final List<ModelDto> modelsDto;
}
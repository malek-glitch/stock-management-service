package com.crocostaud.stockmanagement.dto.part;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.part.Category}
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class CategoryDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final Set<Long> subCategoryIds;
}
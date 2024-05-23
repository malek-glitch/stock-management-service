package com.crocostaud.stockmanagement.dto.part;

import com.crocostaud.stockmanagement.model.part.Part;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Part}
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class PartDto implements Serializable {
    private final Long id;
    private final String ref;
    private final String name;
    private final String imageUrl;

    private final Long subCategoryId;
    private final Set<Long> descriptionIds;
    private final Set<String> oemOems;
    private final String supplierName;
}
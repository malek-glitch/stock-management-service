package com.crocostaud.stockmanagement.dto.part;

import com.crocostaud.stockmanagement.model.part.Part;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link Part}
 */


@Builder
public record PartDto(Long id, String ref, String name, String imageUrl, String supplierName,
                      String category) implements Serializable {
}
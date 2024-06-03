package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.part.SubModel;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link SubModel}
 */

@Builder
public record SubModelDto(Long id, String energyType, int power, String description,
                          Long modelId) implements Serializable {
}
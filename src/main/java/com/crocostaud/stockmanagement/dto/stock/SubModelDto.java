package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.part.SubModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link SubModel}
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class SubModelDto implements Serializable {
    private final Long id;
    private final String energyType;
    private final int power;
    private final String description;
    private final Long modelId;
}
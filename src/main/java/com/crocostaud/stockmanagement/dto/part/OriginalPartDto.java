package com.crocostaud.stockmanagement.dto.part;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.part.OriginalPart}
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class OriginalPartDto implements Serializable {
    private final String oem;
    private final String designation;
}
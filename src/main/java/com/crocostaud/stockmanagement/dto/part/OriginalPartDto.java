package com.crocostaud.stockmanagement.dto.part;

import com.crocostaud.stockmanagement.model.part.Original;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Original}
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
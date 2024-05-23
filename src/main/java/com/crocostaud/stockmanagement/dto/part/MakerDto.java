package com.crocostaud.stockmanagement.dto.part;

import com.crocostaud.stockmanagement.model.part.Maker;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Maker}
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class MakerDto implements Serializable {
    private final Long id;
    private final String name;
    private final String image;
}
package com.crocostaud.stockmanagement.dto.part;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.part.Description}
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class DescriptionDto implements Serializable {
    private final Long id;
    private final String attribute;
    private final String value;
    private final Long partId;
}
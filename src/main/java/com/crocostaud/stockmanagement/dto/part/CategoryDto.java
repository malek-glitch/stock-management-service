package com.crocostaud.stockmanagement.dto.part;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.part.Category}
 */


@Builder
public record CategoryDto(Long id, String name, String description) implements Serializable {
}
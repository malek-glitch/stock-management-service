package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.Client;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link Client}
 */

@Builder
public record ClientDto(Long id, String name, String phone, String email, Long shopId,
                        String address) implements Serializable {
}
package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.Provider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link Provider}
 */
@Builder
@Getter
@Setter
public final class ProviderDto {
    Long id;
    String name;
    String email;
    String phone;
    String address;
    Long shopId;
}
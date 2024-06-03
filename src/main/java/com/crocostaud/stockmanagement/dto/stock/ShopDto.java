package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.Shop;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link Shop}
 */

@Builder
@Getter
public final class ShopDto implements Serializable {
    @Setter
    Long id;
    String name;
    String email;
    String phone;
}
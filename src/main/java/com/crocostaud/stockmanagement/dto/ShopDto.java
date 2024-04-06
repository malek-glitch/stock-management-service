package com.crocostaud.stockmanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.Shop}
 */

@Builder
@Getter
public class ShopDto implements Serializable {
    @Setter
    Long id;
    String name;
    String email;
    String phone;
}
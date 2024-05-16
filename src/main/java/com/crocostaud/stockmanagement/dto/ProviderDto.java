package com.crocostaud.stockmanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.Provider}
 */
@Builder
@Getter
@Setter
public class ProviderDto {
    Long id;
    String name;
    String email;
    String phone;

    Long shopId;

}
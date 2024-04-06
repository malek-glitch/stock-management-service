package com.crocostaud.stockmanagement.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.Provider}
 */
@Value
@Builder
public class ProviderDto {
    Long id;
    String name;
    String email;
    String phone;

    Long shopId;

}
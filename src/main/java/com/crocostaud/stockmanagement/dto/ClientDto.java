package com.crocostaud.stockmanagement.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.crocostaud.stockmanagement.model.Client}
 */
@Value
@Builder
public class ClientDto implements Serializable {
    Long id;
    String name;
    String phone;
}
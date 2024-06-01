package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.Client;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Client}
 */
@Value
@Builder
public class ClientDto implements Serializable {
    Long id;
    String name;
    String phone;
    String email;
    Long shopId;
}
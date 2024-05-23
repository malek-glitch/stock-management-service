package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.Location;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Location}
 */
@Value
public class LocationDto implements Serializable {
    Long id;
    String name;
    String city;
}
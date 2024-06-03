package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.model.stock.Location;

public interface LocationService {
    Location getLocation(Long locationId);

    Location createLocation(Location location);

    Location createLocation(String locationName, String locationAddress);

    Location updateLocation(Location location);

    void deleteLocation(Long locationId);
}

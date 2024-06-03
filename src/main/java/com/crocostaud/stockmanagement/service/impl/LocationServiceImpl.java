package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.model.stock.Location;
import com.crocostaud.stockmanagement.repository.LocationRepository;
import com.crocostaud.stockmanagement.service.LocationService;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepo;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepo = locationRepository;
    }

    @Override
    public Location getLocation(Long locationId) {
        return locationRepo.findById(locationId).orElse(null);
    }

    @Override
    public Location createLocation(Location location) {
        return createLocation(location.getName(), location.getAddress());
    }

    @Override
    public Location createLocation(String locationName, String locationAddress) {
        Location location = new Location(null, locationName, locationAddress);
        return locationRepo.save(location);
    }

    @Override
    public Location updateLocation(Location location) {
        locationRepo.updateNameAndAddressById(location.getName(), location.getAddress(), location.getId());
        return location;
    }

    @Override
    public void deleteLocation(Long locationId) {
        locationRepo.deleteById(locationId);
    }
}

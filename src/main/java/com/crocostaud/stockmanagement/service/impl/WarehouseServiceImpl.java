package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.WarehouseDto;
import com.crocostaud.stockmanagement.model.stock.Location;
import com.crocostaud.stockmanagement.model.stock.Shop;
import com.crocostaud.stockmanagement.model.stock.Warehouse;
import com.crocostaud.stockmanagement.repository.WarehouseRepository;
import com.crocostaud.stockmanagement.service.LocationService;
import com.crocostaud.stockmanagement.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepo;
    private final LocationService locationService;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepo, LocationService locationService) {
        this.warehouseRepo = warehouseRepo;
        this.locationService = locationService;
    }

    @Override
    public WarehouseDto createWarehouse(WarehouseDto warehouseDto, Long shopId) {
        return createWarehouse(warehouseDto, shopId, false);
    }

    @Override
    public WarehouseDto createWarehouse(WarehouseDto warehouseDto, Long shopId, boolean isDefault) {
        Location location = locationService.createLocation(warehouseDto.getLocationName(), warehouseDto.getLocationAddress());
        Warehouse warehouse = Warehouse.builder()
                .name(warehouseDto.getName())
                .location(location)
                .shop(new Shop(shopId))
                .isDefault(isDefault)
                .build();
        Warehouse saved = warehouseRepo.save(warehouse);

        return maToDto(saved);
    }



    @Override
    public WarehouseDto updateWarehouse(WarehouseDto warehouseDto, Long shopId) {
        return null;
    }

    @Override
    public WarehouseDto getDefaultWarehouse(Long shopId) {
        return maToDto(warehouseRepo.findByShop_IdAndIsDefault(shopId, true));
    }

    @Override
    public List<WarehouseDto> getAllWarehouses(Long shopId) {
        return List.of();
    }

    @Override
    public WarehouseDto getWarehouseById(Long id) {
        return null;
    }

    @Override
    public void deleteWarehouse(Long id) {

    }

    WarehouseDto maToDto(Warehouse warehouse) {
        if (warehouse == null)
            return null;
        return WarehouseDto.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .locationName(warehouse.getLocation().getName())
                .locationAddress(warehouse.getLocation().getAddress())
                .build();
    }
}

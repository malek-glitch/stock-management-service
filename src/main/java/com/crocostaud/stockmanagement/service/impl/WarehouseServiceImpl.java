package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.WarehouseDto;
import com.crocostaud.stockmanagement.model.stock.Warehouse;
import com.crocostaud.stockmanagement.repository.WarehouseRepository;
import com.crocostaud.stockmanagement.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepo;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    @Override
    public WarehouseDto createWarehouse(WarehouseDto warehouseDto, Long shopId) {
        return null;
    }

    @Override
    public WarehouseDto updateWarehouse(WarehouseDto warehouseDto, Long shopId) {
        return null;
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

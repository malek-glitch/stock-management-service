package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.WarehouseDto;

import java.util.List;

public interface WarehouseService {
    WarehouseDto createWarehouse(WarehouseDto warehouseDto, Long shopId, boolean isDefault);
    WarehouseDto createWarehouse(WarehouseDto warehouseDto, Long shopId);
    WarehouseDto updateWarehouse(WarehouseDto warehouseDto, Long shopId);

    WarehouseDto getDefaultWarehouse(Long shopId);
    List<WarehouseDto> getAllWarehouses(Long shopId);
    WarehouseDto getWarehouseById(Long id);
    void deleteWarehouse(Long id);
}

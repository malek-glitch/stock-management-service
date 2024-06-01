package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.WarehouseDto;

import java.util.List;

public interface WarehouseService {
    WarehouseDto createWarehouse(WarehouseDto warehouseDto, Long shopId);

    WarehouseDto updateWarehouse(WarehouseDto warehouseDto, Long shopId);

    List<WarehouseDto> getAllWarehouses(Long shopId);

    WarehouseDto getWarehouseById(Long id);

    void deleteWarehouse(Long id);
}

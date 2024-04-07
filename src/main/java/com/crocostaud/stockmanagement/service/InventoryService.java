package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    InventoryDto getInventory(Long id);

    List<InventoryDto> getInventoryByShopId(Long ShopId);

    InventoryDto createInventory(InventoryDto inventoryDto);

    InventoryDto updateInventory(InventoryDto inventoryDto, Long id);

    void deleteInventory(Long id);
}

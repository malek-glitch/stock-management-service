package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.dto.stock.InventoryDto;

import java.util.List;

public interface InventoryService {
    InventoryDto getInventory(Long inventoryId);

    PartDto getPartFromInventory(Long inventoryId);

    List<InventoryDto> getInventoryByShopId(Long ShopId);

    InventoryDto createInventory(InventoryDto inventoryDto);

    InventoryDto updateInventory(InventoryDto inventoryDto, Long id);

    void deleteInventory(Long id);


}

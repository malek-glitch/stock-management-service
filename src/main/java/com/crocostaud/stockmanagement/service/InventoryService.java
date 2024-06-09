package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.dto.stock.InventoryDto;
import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;
import com.crocostaud.stockmanagement.dto.stock.SellItemDto;

import java.util.List;

public interface InventoryService {
    InventoryDto getInventory(Long inventoryId);

    List<InventoryDto> getAllInventory(Long shopId);

    PartDto getPartFromInventory(Long inventoryId);

    List<InventoryDto> getInventoryByShopId(Long ShopId);

    InventoryDto createInventory(InventoryDto inventoryDto);

    InventoryDto createInventory(OrderItemDto orderItemDto, Long shopId);

    InventoryDto updateInventory(SellItemDto sellItemDto, Long shopId);

    InventoryDto updateInventory(InventoryDto inventoryDto, Long id);

    void deleteInventory(Long id);


}

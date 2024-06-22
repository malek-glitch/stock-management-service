package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.ShopDto;
import com.crocostaud.stockmanagement.dto.stock.WarehouseDto;


public interface ShopService {
    ShopDto createShop(ShopDto shopDto, WarehouseDto warehouseDto);
    ShopDto getShop(Long shopId);
    WarehouseDto getDefaultWarehouse(Long shopId);
    ShopDto getShop(String username);
    ShopDto updateShop(ShopDto shopDto, Long id);
    ShopDto delete(Long shopId);
}

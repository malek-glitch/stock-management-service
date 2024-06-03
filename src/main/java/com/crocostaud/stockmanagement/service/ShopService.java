package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.ShopDto;
import com.crocostaud.stockmanagement.dto.stock.WarehouseDto;
import com.crocostaud.stockmanagement.model.stock.*;


public interface ShopService {

    ShopDto createShop(ShopDto shopDto, WarehouseDto warehouseDto);
    ShopDto getShop(Long shopId);

    WarehouseDto getDefaultWarehouse(Long shopId);

    ShopDto getShop(String username);
    ShopDto updateShop(ShopDto shopDto, Long id);

    void addClient(Client client);
    void addSupplier(Provider provider);

    void addInventory(Inventory inventory);
    void addWarehouse(Warehouse warehouse);

    void addSell(Sell sell);
    void addOrder(Order order);

    ShopDto delete(Long shopId);
}

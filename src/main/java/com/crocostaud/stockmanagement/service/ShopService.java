package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.ShopDto;
import com.crocostaud.stockmanagement.model.*;


public interface ShopService {

    ShopDto createShop(ShopDto shopDto);
    ShopDto getShop(Long shopId);
    ShopDto updateShop(ShopDto shopDto, Long id);

    void addClient(Client client);
    void addSupplier(Provider provider);

    void addInventory(Inventory inventory);
    void addWarehouse(Warehouse warehouse);

    void addSell(Sell sell);
    void addOrder(Order order);


}
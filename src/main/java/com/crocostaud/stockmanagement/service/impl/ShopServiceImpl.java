package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.ShopNotFoundException;
import com.crocostaud.stockmanagement.dto.ShopDto;
import com.crocostaud.stockmanagement.model.*;
import com.crocostaud.stockmanagement.repository.ShopRepository;
import com.crocostaud.stockmanagement.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepos;

    public ShopServiceImpl(ShopRepository shopRepos) {
        this.shopRepos = shopRepos;
    }

    @Override
    public ShopDto createShop(ShopDto shopDto) {
        Shop shop = Shop.builder()
                .name(shopDto.getName())
                .email(shopDto.getEmail())
                .phoneNumbers(shopDto.getPhone())
                .build();

        Shop savedShop = shopRepos.save(shop);
        return mapToDto(savedShop);
    }

    @Override
    public ShopDto getShop(Long shopId) {
        Optional<Shop> shop = shopRepos.findById(shopId);
        return shop.map(this::mapToDto).orElse(null);
    }

    private ShopDto mapToDto(Shop shop) {
        return ShopDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .email(shop.getEmail())
                .phone(shop.getPhoneNumbers())
                .build();
    }


    @Override
    public ShopDto updateShop(ShopDto shopDto, Long id) {
        Optional<Shop> shopOptional = shopRepos.findById(id);
        if (shopOptional.isEmpty())
            return null;
        shopRepos.updateNameAndEmailAndPhoneNumbersById(id, shopDto.getName(), shopDto.getEmail(), shopDto.getPhone());
        return mapToDto(shopRepos.findById(id).get());
    }

    @Override
    public void addClient(Client client) {
        //TODO : implement
    }

    @Override
    public void addSupplier(Provider provider) {
        //TODO : implement
    }

    @Override
    public void addInventory(Inventory inventory) {
        //TODO : implement
    }

    @Override
    public void addWarehouse(Warehouse warehouse) {
        //TODO : implement
    }

    @Override
    public void addSell(Sell sell) {
        //TODO : implement
    }

    @Override
    public void addOrder(Order order) {
        //TODO : implement
    }
}

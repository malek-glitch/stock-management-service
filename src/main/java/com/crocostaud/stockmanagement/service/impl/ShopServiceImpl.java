package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.ClientDto;
import com.crocostaud.stockmanagement.dto.stock.ShopDto;
import com.crocostaud.stockmanagement.dto.stock.WarehouseDto;
import com.crocostaud.stockmanagement.model.stock.*;
import com.crocostaud.stockmanagement.repository.ShopRepository;
import com.crocostaud.stockmanagement.service.ClientService;
import com.crocostaud.stockmanagement.service.ShopService;
import com.crocostaud.stockmanagement.service.WarehouseService;
import com.crocostaud.stockmanagement.utils.security.Auth;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepo;
    private final Auth auth;
    private final WarehouseService warehouseService;
    private final ClientService clientService;

    public ShopServiceImpl(ShopRepository shopRepos, Auth auth, WarehouseService warehouseService, ClientService clientService) {
        this.shopRepo = shopRepos;
        this.auth = auth;
        this.warehouseService = warehouseService;
        this.clientService = clientService;
    }

    @Override
    public ShopDto createShop(ShopDto shopDto, WarehouseDto warehouseDto) {
        Shop shop = Shop.builder()
                .name(shopDto.getName())
                .email(shopDto.getEmail())
                .phoneNumbers(shopDto.getPhone())
                .build();

        Shop savedShop = shopRepo.save(shop);
        warehouseService.createWarehouse(warehouseDto, savedShop.getId(), true);
        ClientDto client = new ClientDto(null, "Passager", "+216 24 567 890", "passager@voiture.tn", savedShop.getId(), " adresse Locale");
        clientService.createClient(client, savedShop.getId());
        return mapToDto(savedShop);
    }

    @Override
    public ShopDto getShop(Long shopId) {
        Optional<Shop> shop = shopRepo.findById(shopId);
        return shop.map(this::mapToDto).orElse(null);
    }

    @Override
    public ShopDto getShop(String username) {
        Shop shop = auth.getShop(username);
        if (shop == null)
            return null;
        return mapToDto(shop);
    }

    @Override
    public WarehouseDto getDefaultWarehouse(Long shopId) {
        return warehouseService.getDefaultWarehouse(shopId);
    }


    @Override
    public ShopDto updateShop(ShopDto shopDto, Long id) {
        Optional<Shop> shopOptional = shopRepo.findById(id);
        if (shopOptional.isEmpty())
            return null;
        shopRepo.updateNameAndEmailAndPhoneNumbersById(id, shopDto.getName(), shopDto.getEmail(), shopDto.getPhone());
        return mapToDto(shopRepo.findById(id).get());
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

    @Override
    public ShopDto delete(Long shopId) {
        ShopDto shopDto = getShop(shopId);
        shopRepo.deleteById(shopId);
        return shopDto;
    }

    private ShopDto mapToDto(Shop shop) {
        return ShopDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .email(shop.getEmail())
                .phone(shop.getPhoneNumbers())
                .build();
    }
}

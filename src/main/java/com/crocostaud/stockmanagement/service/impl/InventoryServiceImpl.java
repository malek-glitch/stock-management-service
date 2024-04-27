package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.InventoryDto;
import com.crocostaud.stockmanagement.model.Inventory;
import com.crocostaud.stockmanagement.model.Product;
import com.crocostaud.stockmanagement.model.Shop;
import com.crocostaud.stockmanagement.model.Warehouse;
import com.crocostaud.stockmanagement.repository.InventoryRepository;
import com.crocostaud.stockmanagement.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepo;

    public InventoryServiceImpl(InventoryRepository inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    @Override
    public InventoryDto getInventory(Long id) {
        Optional<Inventory> inventoryOptional = inventoryRepo.findById(id);
        return inventoryOptional.map(this::mapToDto).orElse(null);
    }

    @Override
    public List<InventoryDto> getInventoryByShopId(Long ShopId) {
        return inventoryRepo
                .getInventoriesByShopId(ShopId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {

        Inventory inventory = Inventory.builder()
                .shop(new Shop(inventoryDto.getShopId()))
                .product(new Product(inventoryDto.getProductId()))
                .warehouse(new Warehouse(inventoryDto.getWarehouseId()))
                .minimumStockQuantity(inventoryDto.getMinimumStockQuantity())
                .quantityAvailable(inventoryDto.getQuantityAvailable())
                .price(inventoryDto.getPrice())
                .tva(inventoryDto.getTva())
                .build();

        Inventory savedInventory = inventoryRepo.save(inventory);
        return mapToDto(savedInventory);
    }

    @Override
    public InventoryDto updateInventory(InventoryDto inventoryDto, Long id) {
        Optional<Inventory> inventoryOptional = inventoryRepo.findById(id);

        if (inventoryOptional.isEmpty() || !id.equals(inventoryDto.getId()))
            return null;

        inventoryRepo.updateInventory(inventoryDto.getQuantityAvailable()
                , inventoryDto.getMinimumStockQuantity()
                , inventoryDto.getPrice()
                , inventoryDto.getTva()
                , new Warehouse(inventoryDto.getWarehouseId())
                , id);
        return inventoryDto;
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepo.deleteById(id);
    }

    private InventoryDto mapToDto(Inventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                .shopId(inventory.getShop().getId())
                .productId(inventory.getProduct().getId())
                .warehouseId(inventory.getWarehouse().getId())
                .quantityAvailable(inventory.getQuantityAvailable())
                .minimumStockQuantity(inventory.getMinimumStockQuantity())
                .price(inventory.getPrice())
                .tva(inventory.getTva())
                .build();
    }
}

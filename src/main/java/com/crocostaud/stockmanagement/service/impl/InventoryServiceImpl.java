package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.dto.stock.InventoryDto;
import com.crocostaud.stockmanagement.dto.stock.OrderItemDto;
import com.crocostaud.stockmanagement.dto.stock.SellItemDto;
import com.crocostaud.stockmanagement.dto.stock.WarehouseDto;
import com.crocostaud.stockmanagement.model.part.Part;
import com.crocostaud.stockmanagement.model.stock.Inventory;
import com.crocostaud.stockmanagement.model.stock.Shop;
import com.crocostaud.stockmanagement.model.stock.Warehouse;
import com.crocostaud.stockmanagement.repository.InventoryRepository;
import com.crocostaud.stockmanagement.service.InventoryService;
import com.crocostaud.stockmanagement.service.PartService;
import com.crocostaud.stockmanagement.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final int DEFAULT_MINIMUM_STOCK = 1;
    private final InventoryRepository inventoryRepo;
    private final PartService partService;
    private final ShopService shopService;

    public InventoryServiceImpl(InventoryRepository inventoryRepo, PartService partService, ShopService shopService) {
        this.inventoryRepo = inventoryRepo;
        this.partService = partService;
        this.shopService = shopService;
    }

    @Override
    public InventoryDto getInventory(Long inventoryId) {
        Optional<Inventory> inventoryOptional = inventoryRepo.findById(inventoryId);
        return inventoryOptional.map(this::mapToDto).orElse(null);
    }

    @Override
    public List<InventoryDto> getAllInventory(Long shopId) {
        return inventoryRepo.findByShop_Id(shopId).stream().map(this::mapToDto).toList();
    }

    @Override
    public PartDto getPartFromInventory(Long inventoryId) {
        Optional<Inventory> inventoryOptional = inventoryRepo.findById(inventoryId);
        if (inventoryOptional.isEmpty())
            return null;
        Inventory inventory = inventoryOptional.get();
        return inventory.getPart().toDTO();
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
        Part part = partService.getPart(inventoryDto.getPartId());
        Inventory inventory = Inventory.builder()
                .shop(new Shop(inventoryDto.getShopId()))
                .part(part)
                .partName(part.getName())
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
    public InventoryDto createInventory(OrderItemDto orderItemDto, Long shopId) {
        Inventory inventory = inventoryRepo.findByPart_IdAndShop_IdAndPrice(orderItemDto.getPartId(), shopId, orderItemDto.getPrice());
        if (inventory == null) {
            Part part = partService.getPart(orderItemDto.getPartId());
            WarehouseDto defaultWarehouse = shopService.getDefaultWarehouse(shopId);
            Inventory newInventory = Inventory.builder()
                    .shop(new Shop(shopId))
                    .part(part)
                    .partName(part.getName())
                    .warehouse(new Warehouse(defaultWarehouse.getId()))
                    .minimumStockQuantity(DEFAULT_MINIMUM_STOCK)
                    .quantityAvailable(orderItemDto.getQuantity())
                    .price(orderItemDto.getPrice())
                    .tva(orderItemDto.getTVA())
                    .build();
            Inventory savedInventory = inventoryRepo.save(newInventory);
            return mapToDto(savedInventory);
        } else {
            int updatedQuantity = inventory.getQuantityAvailable() + orderItemDto.getQuantity();
            inventoryRepo.updateQuantityAvailableById(inventory.getId(), updatedQuantity);
            inventory.setQuantityAvailable(updatedQuantity);
        }
        return mapToDto(inventory);
    }

    @Override
    public InventoryDto updateInventory(SellItemDto sellItemDto, Long shopId) {
        Inventory inventory = inventoryRepo.findByPart_IdAndShop_Id(sellItemDto.getPartId(), shopId);
        if (inventory == null) {
            throw new RuntimeException("Inventory not found for part(id): " + sellItemDto.getPartId());
        }
        if (inventory.getQuantityAvailable() < sellItemDto.getQuantity()) {
            throw new RuntimeException("sell exceeds stock limit : " + inventory.getQuantityAvailable());
        }
        int updatedQuantity = inventory.getQuantityAvailable() - sellItemDto.getQuantity();
        inventoryRepo.updateQuantity(updatedQuantity, inventory.getId());
        inventory.setQuantityAvailable(updatedQuantity);
        if (inventory.getQuantityAvailable() == sellItemDto.getQuantity()) {
            inventoryRepo.deleteById(inventory.getId());
        }
        return mapToDto(inventory);
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
                .partId(inventory.getPart().getId())
                .warehouseId(inventory.getWarehouse().getId())
                .quantityAvailable(inventory.getQuantityAvailable())
                .minimumStockQuantity(inventory.getMinimumStockQuantity())
                .price(inventory.getPrice())
                .tva(inventory.getTva())
                .build();
    }
}

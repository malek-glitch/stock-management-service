package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.SellItemDto;
import com.crocostaud.stockmanagement.model.part.Part;
import com.crocostaud.stockmanagement.model.stock.Sell;
import com.crocostaud.stockmanagement.model.stock.SellItem;
import com.crocostaud.stockmanagement.repository.SellItemRepository;
import com.crocostaud.stockmanagement.service.InventoryService;
import com.crocostaud.stockmanagement.service.PartService;
import com.crocostaud.stockmanagement.service.SellItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellItemServiceImpl implements SellItemService {
    private final SellItemRepository itemRepo;
    private final PartService partService;
    private final InventoryService inventoryService;

    public SellItemServiceImpl(SellItemRepository itemRepo, PartService partService, InventoryService inventoryService) {
        this.itemRepo = itemRepo;
        this.partService = partService;
        this.inventoryService = inventoryService;
    }

    @Override
    public List<SellItemDto> getAllItems(Long sellId) {
        return itemRepo.findBySellId(sellId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public SellItemDto getItem(Long sellId, Long itemId) {
        return mapToDto(itemRepo.findBySell_IdAndId(sellId, itemId));
    }

    @Override
    public SellItemDto createSellItem(SellItemDto sellItemDto, Long shopId) {
        Part part = partService.getPart(sellItemDto.getPartId());
        if (part == null)
            throw new RuntimeException("Part not found");

        SellItem sellItem = SellItem.builder()
                .part(part)
                .sell(new Sell(sellItemDto.getSellId()))
                .price(sellItemDto.getPrice())
                .quantity(sellItemDto.getQuantity())
                .TVA(sellItemDto.getTVA())
                .discount(sellItemDto.getDiscount())
                .build();
        SellItem savedsellItem = itemRepo.save(sellItem);
        inventoryService.updateInventory(sellItemDto, shopId);
        return mapToDto(savedsellItem);
    }

    @Override
    public SellItemDto updateSellItem(SellItemDto sellItemDto, Long id) {
        return null;
    }

    @Override
    public void deleteSellItem(Long id) {
        itemRepo.deleteById(id);
    }

    private SellItemDto mapToDto(SellItem sellItem) {
        return SellItemDto.builder()
                .sellId(sellItem.getSell().getId())
                .partId(sellItem.getPart().getId())
                .partName(sellItem.getPart().getName())
                .id(sellItem.getId())
                .price(sellItem.getPrice())
                .quantity(sellItem.getQuantity())
                .TVA(sellItem.getTVA())
                .discount(sellItem.getDiscount())
                .build();
    }
}

package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.ClientDto;
import com.crocostaud.stockmanagement.dto.stock.SellDto;
import com.crocostaud.stockmanagement.dto.stock.SellItemDto;
import com.crocostaud.stockmanagement.model.part.Part;
import com.crocostaud.stockmanagement.model.stock.Client;
import com.crocostaud.stockmanagement.model.stock.Sell;
import com.crocostaud.stockmanagement.model.stock.Shop;
import com.crocostaud.stockmanagement.repository.SellRepository;
import com.crocostaud.stockmanagement.service.ClientService;
import com.crocostaud.stockmanagement.service.PartService;
import com.crocostaud.stockmanagement.service.SellItemService;
import com.crocostaud.stockmanagement.service.SellService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SellServiceImpl implements SellService {
    private final SellItemService itemService;
    private final SellRepository sellRepo;
    private final PartService partService;
    private final ClientService clientService;

    public SellServiceImpl(SellItemService itemService, SellRepository sellRepo, PartService partService, ClientService clientService) {
        this.itemService = itemService;
        this.sellRepo = sellRepo;
        this.partService = partService;
        this.clientService = clientService;
    }

    @Override
    public List<SellDto> getSells(Long shopId) {
        return sellRepo.findByShop(shopId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public SellDto getSell(Long SellId) {
        return mapToDto(sellRepo.findById(SellId).orElse(null));
    }

    @Override
    public List<SellItemDto> getSellItems(Long SellId) {
        return itemService.getAllItems(SellId);
    }

    @Override
    public SellItemDto getSellItem(Long SellId, Long ItemId) {
        return itemService.getItem(SellId, ItemId);
    }

    @Override
    public SellDto createSell(SellDto SellDto, List<SellItemDto> SellItems) {
        ClientDto client = clientService.getClient(SellDto.clientId());
        if (client == null)
            throw new RuntimeException("client(" + SellDto.clientId() + ") not found");
        Sell sell = Sell.builder()
                .client(new Client(SellDto.clientId()))
                .shop(new Shop(SellDto.shopId()))
                .paidAmount(SellDto.paidAmount())
                .discount(SellDto.discount())
                .totalPrice(calculateTotalPrice(SellItems))
                .date(LocalDateTime.now())
                .build();
        Sell savedSell = sellRepo.save(sell);
        try {
            addItemsToSell(SellItems, savedSell.getId(), SellDto.shopId());
        } catch (Exception e) {
            sellRepo.delete(sell);
            return null;
        }
        return mapToDto(sell);
    }

    private Double calculateTotalPrice(List<SellItemDto> sellItems) {
        return sellItems.stream().mapToDouble(item -> {
            double price = (item.getPrice() + item.getPrice() * item.getTVA() / 100) * item.getQuantity();
            double discount = price * item.getDiscount() / 100;
            return price - discount;
        }).sum();
    }

    @Override
    public void addItemsToSell(List<SellItemDto> sellItems, Long SellId, Long shopId) {

        for (SellItemDto item : sellItems) {
            Part part = partService.getPart(item.getPartId());
            item.setPartName(part.getName());
            item.setSellId(SellId);
            try {
                itemService.createSellItem(item, shopId);
            } catch (Exception e) {
                sellItems.remove(item);
            }
        }
        if (sellItems.isEmpty())
            throw new RuntimeException("sell items is empty");
    }

    @Override
    public SellDto updateSell(SellDto SellDto, Long SellId) {
        //TODO : implement
        return null;
    }

    @Override
    public void deleteSell(Long id) {
        sellRepo.deleteById(id);
    }

    private SellDto mapToDto(Sell sell) {
        return SellDto.builder().id(sell.getId())
                .shopId(sell.getShop().getId())
                .clientId(sell.getClient().getId())
                .date(sell.getDate())
                .totalPrice(sell.getTotalPrice())
                .paidAmount(sell.getPaidAmount())
                .discount(sell.getDiscount())
                .build();
    }
}

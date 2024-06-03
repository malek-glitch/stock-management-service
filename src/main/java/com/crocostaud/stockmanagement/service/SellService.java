package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.SellDto;
import com.crocostaud.stockmanagement.dto.stock.SellItemDto;

import java.util.List;

public interface SellService {
    List<SellDto> getSells(Long shopId);

    SellDto getSell(Long SellId);

    List<SellItemDto> getSellItems(Long SellId);

    SellItemDto getSellItem(Long SellId, Long ItemId);

    SellDto createSell(SellDto SellDto, List<SellItemDto> SellItems);

    void addItemsToSell(List<SellItemDto> SellItems, Long SellId, Long shopId);

    SellDto updateSell(SellDto SellDto, Long SellId);

    void deleteSell(Long id);
}

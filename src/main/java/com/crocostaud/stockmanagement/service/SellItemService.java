package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.SellItemDto;

import java.util.List;

public interface SellItemService {
    List<SellItemDto> getAllItems(Long sellId);

    SellItemDto getItem(Long sellId, Long itemId);

    SellItemDto createSellItem(SellItemDto sellItemDto, Long shopId);

    SellItemDto updateSellItem(SellItemDto sellItemDto, Long id);

    void deleteSellItem(Long id);
}

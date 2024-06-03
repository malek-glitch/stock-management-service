package com.crocostaud.stockmanagement.utils.request;

import com.crocostaud.stockmanagement.dto.stock.SellDto;
import com.crocostaud.stockmanagement.dto.stock.SellItemDto;

import java.util.List;

public record SellRequest(SellDto sell, List<SellItemDto> items) {
}

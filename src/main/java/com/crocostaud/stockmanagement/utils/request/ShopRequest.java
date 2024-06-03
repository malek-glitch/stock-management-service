package com.crocostaud.stockmanagement.utils.request;

import com.crocostaud.stockmanagement.dto.stock.ShopDto;
import com.crocostaud.stockmanagement.dto.stock.WarehouseDto;

public record ShopRequest(ShopDto shopDto, WarehouseDto warehouseDto) {
}

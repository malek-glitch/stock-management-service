package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.ShopDto;
import com.crocostaud.stockmanagement.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/")
    public ResponseEntity<ShopDto> create(@RequestBody ShopDto shopDto) {
        ShopDto savedShop = shopService.createShop(shopDto);
        return ResponseEntity.ok(savedShop);
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<ShopDto> get(@PathVariable Long shopId) {
        ShopDto shop = shopService.getShop(shopId);

        if (shop == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(shop);
    }

}

package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.stock.SellDto;
import com.crocostaud.stockmanagement.dto.stock.SellItemDto;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.SellItemService;
import com.crocostaud.stockmanagement.service.SellService;
import com.crocostaud.stockmanagement.utils.annotation.User;
import com.crocostaud.stockmanagement.utils.request.SellRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sell")
public class SellController {

    private final SellService sellService;
    private final SellItemService sellItemService;

    public SellController(SellService sellService, SellItemService sellItemService) {
        this.sellService = sellService;
        this.sellItemService = sellItemService;
    }

    @GetMapping("/{SellId}")
    public ResponseEntity<SellRequest> sell(@PathVariable Long SellId, @User ShopUser user) {
        SellDto sell = sellService.getSell(SellId);
        if (sell == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Sell not found");
        }
        if (sell.shopId() != user.getShop().getId()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "User not logged in");
        }
        List<SellItemDto> sellItems = sellService.getSellItems(sell.id());
        return ResponseEntity.ok(new SellRequest(sell, sellItems));
    }

    @GetMapping
    public ResponseEntity<Iterable<SellDto>> sell(@User ShopUser user) {
        if (user == null || user.getShop() == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "User not logged in");
        }
        Long shopId = user.getShop().getId();
        Iterable<SellDto> sells = sellService.getSells(shopId);
        return ResponseEntity.ok(sells);
    }

    @PostMapping
    public ResponseEntity<SellDto> sell(@RequestBody SellRequest sellRequest) {
        if (sellRequest == null || sellRequest.sell() == null || sellRequest.items() == null || sellRequest.items().isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Sell request not valid");
        }
        SellDto sell = sellService.createSell(sellRequest.sell(), sellRequest.items());
        if (sell == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Sell creation failed");
        return ResponseEntity.ok(sell);
    }


    @DeleteMapping("/{sellId}")
    public ResponseEntity<Void> delete(@PathVariable Long SellId, @User ShopUser user) {
        if (user == null || user.getShop() == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "User not logged in");
        }
        SellDto sell = sellService.getSell(SellId);
        if (sell == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Sell not found");
        }
        if (sell.shopId() != user.getShop().getId()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "User not logged in");
        }
        sellService.deleteSell(sell.id());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{sellId}/item/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable Long SellId, @PathVariable Long itemId, @User ShopUser user) {
        if (user == null || user.getShop() == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "User not logged in");
        }
        SellDto sell = sellService.getSell(SellId);
        if (sell == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Sell not found");
        }
        if (sell.shopId() != user.getShop().getId()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "User dont have permission to delete this item ");
        }
        SellItemDto item = sellService.getSellItem(sell.id(), itemId);
        if (item == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Item not found");
        sellItemService.deleteSellItem(itemId);
        return ResponseEntity.noContent().build();
    }
}

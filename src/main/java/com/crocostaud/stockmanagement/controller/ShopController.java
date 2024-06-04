package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.stock.ShopDto;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.ShopService;
import com.crocostaud.stockmanagement.service.UserService;
import com.crocostaud.stockmanagement.utils.annotation.User;
import com.crocostaud.stockmanagement.utils.request.ShopRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;
    private final UserService userService;

    public ShopController(ShopService shopService, UserService userService) {
        this.shopService = shopService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ShopDto> create(@RequestBody ShopRequest shopRequest, @User ShopUser user) {
        if (user == null || user.getShop() != null) {
            return ResponseEntity.badRequest().build();
        }
        ShopDto savedShop = shopService.createShop(shopRequest.shopDto(), shopRequest.warehouseDto());
        userService.setShop(user.getId(), savedShop.getId());

        return ResponseEntity.ok(savedShop);
    }

    @PutMapping
    public ResponseEntity<ShopDto> update(@RequestBody ShopDto shopDto, @User ShopUser user) {
        if (user == null || user.getShop() == null || user.getShop().getId() == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "User not found");

        if (!user.getShop().getId().equals(shopDto.getId()))
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Shop not found");

        ShopDto updatedShop = shopService.updateShop(shopDto, user.getShop().getId());
        return ResponseEntity.ok(updatedShop);
    }

    @GetMapping
    public ResponseEntity<ShopDto> get(@User ShopUser user) {
        ShopDto shop = shopService.getShop(user.getUsername());

        if (shop == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(shop);
    }

    @DeleteMapping("/{shopId}")
    public ResponseEntity<ShopDto> delete(@PathVariable Long shopId, @User ShopUser user) {
        if (user == null || user.getShop() == null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
        ShopDto shop = shopService.getShop(shopId);
        return ResponseEntity.ok(shopService.delete(shop.getId()));
    }
}

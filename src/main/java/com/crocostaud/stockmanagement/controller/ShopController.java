package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.stock.ShopDto;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.ShopService;
import com.crocostaud.stockmanagement.service.UserService;
import com.crocostaud.stockmanagement.utils.annotation.Username;
import com.crocostaud.stockmanagement.utils.security.Auth;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;
    private final UserService userService;
    private final Auth auth;

    public ShopController(ShopService shopService, Auth auth, UserService userService) {
        this.shopService = shopService;
        this.auth = auth;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ShopDto> create(@RequestBody ShopDto shopDto, @Username String username) {
        ShopUser user = auth.getUser(username);
        if (user == null || user.getShop() != null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(405)).build();
        }
        ShopDto savedShop = shopService.createShop(shopDto);
        userService.setShop(user.getId(), savedShop.getId());
        return ResponseEntity.ok(savedShop);
    }

    @PutMapping
    public ResponseEntity<ShopDto> update(@RequestBody ShopDto shopDto, @Username String username) {
        ShopUser user = auth.getUser(username);
        System.out.println(username + "  -> " + user);
        if (user == null || user.getShop() == null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }

        if (!user.getShop().getId().equals(shopDto.getId()))
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();

        ShopDto updatedShop = shopService.updateShop(shopDto, user.getShop().getId());
        return ResponseEntity.ok(updatedShop);
    }

    @GetMapping
    public ResponseEntity<ShopDto> get(@Username String username) {
        ShopUser user = auth.getUser(username);
        System.out.println(username + "  -> " + user);

        ShopDto shop = shopService.getShop(username);

        if (shop == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(shop);
    }

    @DeleteMapping("/{shopId}")
    public ResponseEntity<ShopDto> delete(@Username String username, @PathVariable Long shopId) {
        ShopUser user = auth.getUser(username);
//        if (user == null || user.getShop() == null) {
//            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
//        }
        ShopDto shop = shopService.getShop(shopId);
        return ResponseEntity.ok(shopService.delete(shop.getId()));

    }

}

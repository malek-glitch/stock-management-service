package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.stock.InventoryDto;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.InventoryService;
import com.crocostaud.stockmanagement.utils.annotation.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/stock")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryDto> create(@RequestBody InventoryDto inventoryDto) {
        InventoryDto inventory = inventoryService.createInventory(inventoryDto);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAll(@User ShopUser shopUser) {
        if (shopUser == null || shopUser.getShop() == null) {
            return ResponseEntity.noContent().build();
        }
        List<InventoryDto> allInventory = inventoryService.getInventoryByShopId(shopUser.getShop().getId());
        System.out.println("--------------------------------------");
        System.out.println(Arrays.toString(allInventory.toArray()));
        System.out.println("--------------------------------------");
        return ResponseEntity.ok(allInventory);
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryDto> get(@PathVariable Long inventoryId) {
        InventoryDto inventory = inventoryService.getInventory(inventoryId);
        if (inventory == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(inventory);
    }

    @DeleteMapping("/{inventoryId}")
    ResponseEntity<Void> delete(@PathVariable Long inventoryId) {
        inventoryService.deleteInventory(inventoryId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{inventoryId}")
    ResponseEntity<InventoryDto> update(@RequestBody InventoryDto inventoryDto, @PathVariable Long inventoryId) {
        InventoryDto updated = inventoryService.updateInventory(inventoryDto, inventoryId);
        if (updated == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(updated);
    }

}

package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.stock.InventoryDto;
import com.crocostaud.stockmanagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/stock")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/")
    public ResponseEntity<InventoryDto> create(@RequestBody InventoryDto inventoryDto) {
        InventoryDto inventory = inventoryService.createInventory(inventoryDto);
        return ResponseEntity.ok(inventory);
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

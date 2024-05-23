package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Inventory;
import com.crocostaud.stockmanagement.model.stock.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Transactional
    @Modifying
    @Query("""
            update Inventory i
            set   i.quantityAvailable = ?1,
                  i.minimumStockQuantity = ?2,
                  i.price = ?3, i.tva = ?4,
                  i.warehouse = ?5
            where i.id = ?6
            """)
    void updateInventory(int quantityAvailable, int minimumStockQuantity, Double price, int tva, Warehouse warehouse, Long id);

    List<Inventory> getInventoriesByShopId(Long shop_id);
}
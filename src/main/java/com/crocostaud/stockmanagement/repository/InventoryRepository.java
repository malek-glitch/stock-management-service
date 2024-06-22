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

    @Query("select i from Inventory i where i.part.id = ?1 and i.shop.id = ?2 and i.price = ?3")
    Inventory findByPart_IdAndShop_IdAndPrice(Long partId, Long shopId, Double price);

    @Query("select i from Inventory i where i.part.id = ?1 and i.shop.id = ?2")
    Inventory findByPart_IdAndShop_Id(Long partId, Long shopId);

    @Transactional
    @Modifying
    @Query("update Inventory i set i.quantityAvailable = ?2 where i.id = ?1")
    void updateQuantityAvailableById(Long id, int quantityAvailable);

    @Transactional
    @Modifying
    @Query("update Inventory i set i.quantityAvailable = ?1 where i.id = ?2")
    void updateQuantity(int quantityAvailable, Long id);

    @Query("select i from Inventory i where i.shop.id = ?1")
    List<Inventory> findByShop_Id(Long id);

    @Query("select i from Inventory i where i.part.id = ?1")
    List<Inventory> findByPart_Id(Long id);
}
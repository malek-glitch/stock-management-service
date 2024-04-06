package com.crocostaud.stockmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

/**This entity represents the relationship between products and warehouses.
 *  Each {@link Product product} may exist in several {@link Warehouse warehouses},
 *  and each {@link Warehouse} may contain many {@link Product products}.*/
@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantityAvailable;
    private int minimumStockQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
}

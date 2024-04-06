package com.crocostaud.stockmanagement;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(String shopNotFound) {
        super(shopNotFound);
    }
}

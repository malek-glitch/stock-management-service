package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.model.stock.ShopUser;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(ShopUser shopUser);

    boolean isTokenValid(String token, ShopUser shopUser);
}

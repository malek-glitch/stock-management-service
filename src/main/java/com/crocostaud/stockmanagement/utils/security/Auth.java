package com.crocostaud.stockmanagement.utils.security;

import com.crocostaud.stockmanagement.model.Shop;
import com.crocostaud.stockmanagement.model.ShopUser;
import com.crocostaud.stockmanagement.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class Auth {

    private final UserRepository userRepo;

    public Auth(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Shop getShop(String username) {
        ShopUser user = getUser(username);
        if (user != null)
            return user.getShop();
        return null;
    }

    public ShopUser getUser(String username) {
        return userRepo.findByUsername(username);
    }

}

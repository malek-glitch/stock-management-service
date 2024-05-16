package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.Shop;
import com.crocostaud.stockmanagement.model.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<ShopUser, Long> {
    ShopUser findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update user u set u.username = ?1, u.password = ?2, u.email = ?3 where u.id = ?4")
    void updateUsernameAndPasswordAndEmailByIdAllIgnoreCase(String username, String password, String email, Long id);

    @Transactional
    @Modifying
    @Query("update user u set u.shop = ?1 where u.id = ?2")
    int setShopById(Shop shop, Long id);
}
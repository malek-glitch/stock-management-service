package com.crocostaud.stockmanagement.repository;


import com.crocostaud.stockmanagement.model.stock.Shop;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<ShopUser, Long> {
    ShopUser findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update users u set u.username = ?1, u.password = ?2, u.email = ?3 where u.id = ?4")
    void updateUsernameAndPasswordAndEmailByIdAllIgnoreCase(String username, String password, String email, Long id);

    @Transactional
    @Modifying
    @Query("update users u set u.shop = ?1 where u.id = ?2")
    void setShopById(Shop shop, Long id);
    @Transactional
    @Modifying
    @Query("update users u set u.username = ?1, u.password = ?2, u.email = ?3, u.phone = ?4 where u.id = ?5")
    void updateUsernameAndPasswordAndEmailAndPhoneByIdAllIgnoreCase(String username, String password, String email, String phone, Long id);

    @Query("select u from users u where u.shop.id = ?1")
    List<ShopUser> findByShop_Id(Long id);
}
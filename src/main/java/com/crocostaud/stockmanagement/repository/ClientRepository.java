package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Client;
import com.crocostaud.stockmanagement.model.stock.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Transactional
    @Modifying
    @Query("update Client c set c.name = ?1, c.phone = ?2 where c.id = ?3")
    void updateNameAndPhoneById(String name, String phone, Long id);

    @Query("select c from Client c where c.shop.id = ?1")
    List<Client> findAllByShopId(Long id);

    @Transactional
    @Modifying
    @Query("delete from Client c where c.id = ?1 and c.shop = ?2")
    void deleteByIdAndShop_id(Long id, Shop shop);
}
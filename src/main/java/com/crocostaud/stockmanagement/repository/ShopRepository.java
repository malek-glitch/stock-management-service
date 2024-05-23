package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Transactional
    @Modifying
    @Query("update Shop s set s.name = ?1, s.email = ?2 where s.id = ?3")
    int updateNameAndEmailById(String name, String email, Long id);

    @Transactional
    @Modifying
    @Query("update Shop s set s.name = ?1, s.email = ?2, s.phoneNumbers = ?3")
    int updateNameAndEmailAndPhoneNumbersBy(String name, String email, String phoneNumbers);

    @Transactional
    @Modifying
    @Query("update Shop s set s.name = ?2, s.email = ?3, s.phoneNumbers = ?4 where s.id = ?1")
    int updateNameAndEmailAndPhoneNumbersById(Long id, String name, String email, String phoneNumbers);

    void deleteById(Long shopId);
}
package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Transactional
    @Modifying
    @Query("update Client c set c.name = ?1, c.phone = ?2 where c.id = ?3")
    void updateNameAndPhoneById(String name, String phone, Long id);

}
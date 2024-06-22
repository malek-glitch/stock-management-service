package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Transactional
    @Modifying
    @Query("update Provider p set p.name = ?1, p.email = ?2, p.phone = ?3, p.address = ?4 where p.id = ?5")
    void update(String name, String email, String phone, String address, Long id);

    List<Provider> findByShop_Id(Long id);
}
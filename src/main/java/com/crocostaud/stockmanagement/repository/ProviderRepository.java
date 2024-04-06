package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Transactional
    @Modifying
    @Query("update Provider p set p.name = ?1, p.email = ?2, p.phone = ?3 where p.id = ?4")
    int updateNameAndEmailAndPhoneById(String name, String email, String phone, Long id);
}
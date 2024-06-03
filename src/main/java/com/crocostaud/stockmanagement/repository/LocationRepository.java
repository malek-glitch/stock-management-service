package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Transactional
    @Modifying
    @Query("update Location l set l.name = ?1, l.address = ?2 where l.id = ?3")
    void updateNameAndAddressById(String name, String address, Long id);
}
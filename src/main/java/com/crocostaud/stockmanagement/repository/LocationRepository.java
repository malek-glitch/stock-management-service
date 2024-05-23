package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.stock.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
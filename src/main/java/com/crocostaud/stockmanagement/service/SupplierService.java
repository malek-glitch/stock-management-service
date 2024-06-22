package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.SupplierDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService {
    List<SupplierDto> getAllSuppliers();

    SupplierDto getSupplier(String name);

    void deleteSupplier(String name);
}

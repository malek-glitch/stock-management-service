package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.part.SupplierDto;
import com.crocostaud.stockmanagement.model.part.Supplier;
import com.crocostaud.stockmanagement.repository.SupplierRepository;
import com.crocostaud.stockmanagement.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepo;

    public SupplierServiceImpl(SupplierRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    @Override
    public List<SupplierDto> getAllSuppliers() {
        return supplierRepo.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    public SupplierDto getSupplier(String name) {
        return mapToDto(supplierRepo.findById(name).orElse(null));
    }

    @Override
    public void deleteSupplier(String name) {
        supplierRepo.deleteById(name);
    }

    private SupplierDto mapToDto(Supplier supplier) {
        if (supplier == null) {
            return null;
        }
        return SupplierDto.builder()
                .name(supplier.getName())
                .image(supplier.getImage())
                .description(supplier.getDescription())
                .build();
    }
}

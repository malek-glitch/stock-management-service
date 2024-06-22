package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.part.PartDetail;
import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.model.part.Part;
import com.crocostaud.stockmanagement.repository.PartRepository;
import com.crocostaud.stockmanagement.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepo;
    private final CategoryService categoryService;
    private final SupplierService supplierService;
    private final InventoryService inventoryService;
    private final DescriptionService descriptionService;
    private final OriginalPartService originalPartService;


    public PartServiceImpl(PartRepository partRepository, CategoryService categoryService, SupplierService supplierService, InventoryService inventoryService, DescriptionService descriptionService, OriginalPartService originalPartService) {
        this.partRepo = partRepository;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
        this.inventoryService = inventoryService;
        this.descriptionService = descriptionService;
        this.originalPartService = originalPartService;

    }
    @Override
    public Part getPart(Long id) {
        return partRepo.findById(id).orElse(null);
    }

    @Override
    public PartDetail getPartDetail(Long id) {
        Part part = getPart(id);
        if (part == null)
            return null;
        return PartDetail.builder()
                .id(part.getId())
                .name(part.getName())
                .ref(part.getRef())
                .category(categoryService.getCategoryById(part.getCategory().getId()))
                .supplier(supplierService.getSupplier(part.getSupplier().getName()))
                .inventories(inventoryService.getInventoryByPartId(id))
                .descriptions(descriptionService.getDescriptionsByPart(id))
                .originals(originalPartService.getAllOriginalsByPartId(id))
                .build();
    }

    @Override
    public PartDto getPartDto(Long id) {
        return null;
    }

    @Override
    public List<Part> getAllParts() {
        return List.of();
    }


    @Override
    public boolean addPart(Part part) {
        return false;
    }
}

package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.model.part.Part;
import com.crocostaud.stockmanagement.service.PartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    @Override
    public Part getPart(Long id) {
        return null;
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
    public List<Part> getPartsByCategory(Long categoryId) {
        return List.of();
    }

    @Override
    public List<Part> getAllPartsByCategory(Long categoryId) {
        return List.of();
    }

    @Override
    public boolean addPart(Part part) {
        return false;
    }
}

package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.model.part.Part;

import java.util.List;

public interface PartService {
    Part getPart(Long id);

    PartDto getPartDto(Long id);

    List<Part> getAllParts();

    List<Part> getPartsByCategory(Long categoryId);

    List<Part> getAllPartsByCategory(Long categoryId);

    boolean addPart(Part part);
}

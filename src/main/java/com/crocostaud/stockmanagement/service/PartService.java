package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.PartDetail;
import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.model.part.Part;

import java.util.List;

public interface PartService {
    Part getPart(Long id);

    PartDetail getPartDetail(Long id);

    PartDto getPartDto(Long id);

    List<Part> getAllParts();

    boolean addPart(Part part);
}

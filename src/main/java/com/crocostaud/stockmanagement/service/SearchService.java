package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.PartDto;

import java.util.List;

public interface SearchService {
    List<PartDto> searchPartByName(String partName);

    List<PartDto> searchPartByRef(String ref);

    List<PartDto> searchPartByCategory(Long categoryId);

    List<PartDto> searchPartByCategoryAndName(Long categoryId, String partName);

    List<PartDto> searchPartByCategoryAndModel(Long categoryId, Long modelId);

    List<PartDto> searchPartByCategoryAndSubmodel(Long categoryId, Long submodelId);

    List<PartDto> searchPartBySubmodelAndName(Long submodelId);

    List<PartDto> searchByModel(Long modelId);

    List<PartDto> searchByModel(Long modelId, String partName);
}

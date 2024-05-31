package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.PartDto;

import java.util.List;

public interface SearchService {
    List<PartDto> searchByText(String text);

    List<PartDto> searchByCategory(Long categoryId, String text);

    List<PartDto> searchBySubmodel(Long submodelId, String text);

    List<PartDto> searchBySubmodel(Long submodelId);

    List<PartDto> searchByModel(Long modelId, String text);
    List<PartDto> searchByModel(Long modelId);

    List<PartDto> searchByCategoryAndModel(Long categoryId, Long modelId);

    List<PartDto> searchByCategoryAndSubmodel(Long categoryId, Long submodelId);

    List<PartDto> searchEquivalentPart(Long PartId);
}

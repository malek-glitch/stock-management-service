package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.model.part.Category;
import com.crocostaud.stockmanagement.model.part.Part;
import com.crocostaud.stockmanagement.repository.PartRepository;
import com.crocostaud.stockmanagement.service.SearchService;

import java.util.List;


public class SearchServiceImpl implements SearchService {

    private final PartRepository partRepo;

    public SearchServiceImpl(PartRepository partRepos) {
        this.partRepo = partRepos;
    }

    @Override
    public List<PartDto> searchPartByName(String partName) {
        return partRepo.findByNameLikeIgnoreCase(partName).stream().map(Part::toDTO).toList();
    }


    @Override
    public List<PartDto> searchPartByRef(String ref) {
        return partRepo.findByRefLikeIgnoreCase(ref).stream().map(Part::toDTO).toList();
    }

    @Override
    public List<PartDto> searchPartByCategory(Long categoryId) {
        return partRepo.findByCategory(new Category(categoryId)).stream().map(Part::toDTO).toList();
    }

    @Override
    public List<PartDto> searchPartByCategoryAndName(Long categoryId, String partName) {
        return List.of();
    }

    @Override
    public List<PartDto> searchPartByCategoryAndModel(Long categoryId, Long modelId) {
        return List.of();
    }

    @Override
    public List<PartDto> searchPartByCategoryAndSubmodel(Long categoryId, Long submodelId) {
        return List.of();
    }

    @Override
    public List<PartDto> searchPartBySubmodelAndName(Long submodelId) {
        return List.of();
    }

    @Override
    public List<PartDto> searchByModel(Long modelId) {
        return List.of();
    }

    @Override
    public List<PartDto> searchByModel(Long modelId, String partName) {
        return List.of();
    }
}

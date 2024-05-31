package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.model.part.Part;
import com.crocostaud.stockmanagement.repository.PartRepository;
import com.crocostaud.stockmanagement.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SearchServiceImpl implements SearchService {
    private final PartRepository partRepo;

    public SearchServiceImpl(PartRepository partRepos) {
        this.partRepo = partRepos;
    }
    @Override
    public List<PartDto> searchByText(String text) {
        List<Part> result = partRepo.findByText(text);
        return toDto(result);
    }

    @Override
    public List<PartDto> searchByCategory(Long categoryId, String text) {
        List<Part> result = partRepo.findByCategory(categoryId, text);
        return toDto(result);
    }

    @Override
    public List<PartDto> searchBySubmodel(Long submodelId, String text) {
        List<Part> result = partRepo.findBySubmodel(submodelId, text);
        return toDto(result);
    }

    @Override
    public List<PartDto> searchBySubmodel(Long submodelId) {
        List<Part> result = partRepo.findBySubmodel(submodelId);
        return toDto(result);
    }

    @Override
    public List<PartDto> searchByModel(Long modelId, String text) {
        List<Part> result = partRepo.findByModel(modelId, text);
        return toDto(result);
    }

    @Override
    public List<PartDto> searchByModel(Long modelId) {
        List<Part> result = partRepo.findByModel(modelId);
        return toDto(result);
    }

    @Override
    public List<PartDto> searchByCategoryAndModel(Long categoryId, Long modelId) {
        List<Part> result = partRepo.findByCategoryAndModel(categoryId, modelId);
        return toDto(result);
    }

    @Override
    public List<PartDto> searchByCategoryAndSubmodel(Long categoryId, Long submodelId) {
        List<Part> result = partRepo.findByCategoryAndSubmodel(categoryId, submodelId);
        return toDto(result);
    }

    @Override
    public List<PartDto> searchEquivalentPart(Long PartId) {
        Optional<Part> part = partRepo.findById(PartId);
//        if (part.isEmpty())
        return null;
//        List<Part> equivalentParts = partRepo.findByOemsInAndIdNot(part.get().getOems(), PartId);
//        return toDto(equivalentParts);

    }

    private PartDto mapToDto(Part part) {
        return PartDto.builder()
                .id(part.getId())
                .name(part.getName())
                .ref(part.getRef())
                .imageUrl(part.getImageUrl())
                .supplierName(part.getSupplier().getName())
                .category(part.getCategory().getName())
                .build();
    }

    private List<PartDto> toDto(List<Part> parts) {
        return parts.stream().map(this::mapToDto).toList();
    }
}

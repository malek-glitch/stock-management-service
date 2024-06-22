package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.part.DescriptionDto;
import com.crocostaud.stockmanagement.model.part.Description;
import com.crocostaud.stockmanagement.repository.DescriptionRepository;
import com.crocostaud.stockmanagement.service.DescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    private final DescriptionRepository descriptionRepo;

    public DescriptionServiceImpl(DescriptionRepository descriptionRepo) {
        this.descriptionRepo = descriptionRepo;
    }

    @Override
    public List<DescriptionDto> getDescriptionsByPart(long partId) {
        List<Description> descriptions = descriptionRepo.findByPart_Id(partId);
        return descriptions.stream().map(this::mapToDto).toList();
    }

    DescriptionDto mapToDto(Description description) {
        if (description == null) {
            return null;
        }
        return DescriptionDto.builder()
                .attribute(description.getAttribute())
                .id(description.getId())
                .value(description.getValue())
                .build();
    }
}

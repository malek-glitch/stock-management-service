package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.part.OriginalPartDto;
import com.crocostaud.stockmanagement.model.part.Original;
import com.crocostaud.stockmanagement.repository.OriginalPartRepository;
import com.crocostaud.stockmanagement.service.OriginalPartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginalServiceImpl implements OriginalPartService {

    private final OriginalPartRepository originalRepo;

    public OriginalServiceImpl(OriginalPartRepository originalRepo) {
        this.originalRepo = originalRepo;
    }

    @Override
    public OriginalPartDto getOriginalPart(String oem) {
        return maoToDto(originalRepo.findById(oem).orElse(null));
    }

    @Override
    public OriginalPartDto saveOriginalPart(OriginalPartDto originalPartDto) {
        return null;
    }

    @Override
    public List<OriginalPartDto> getAllOriginalsByPartId(long partId) {
        List<Original> originals = originalRepo.findByParts_Id(partId);
        return originals.stream().map(this::maoToDto).toList();
    }

    OriginalPartDto maoToDto(Original original) {
        if (original == null) {
            return null;
        }
        return OriginalPartDto.builder()
                .oem(original.getOem())
                .designation(original.getDesignation())
                .build();
    }
}

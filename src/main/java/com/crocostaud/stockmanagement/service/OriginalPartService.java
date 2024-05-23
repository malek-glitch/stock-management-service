package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.OriginalPartDto;
import org.springframework.stereotype.Service;

@Service
public interface OriginalPartService {
    OriginalPartDto getOriginalPart(String oem);

    OriginalPartDto saveOriginalPart(OriginalPartDto originalPartDto);

}

package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.OriginalPartDto;

import java.util.List;


public interface OriginalPartService {
    OriginalPartDto getOriginalPart(String oem);

    OriginalPartDto saveOriginalPart(OriginalPartDto originalPartDto);

    List<OriginalPartDto> getAllOriginalsByPartId(long partId);

}
